package com.ebanma.cloud.game.service.impl;

import com.ebanma.cloud.common.core.ServiceException;
import com.ebanma.cloud.common.enums.GameEggEnum;
import com.ebanma.cloud.common.enums.GamePriceOrPropEnum;
import com.ebanma.cloud.common.enums.GameRedisEnum;
import com.ebanma.cloud.common.enums.TransRocketMQEnum;
import com.ebanma.cloud.game.model.dto.GameDrawDto;
import com.ebanma.cloud.game.model.dto.GamePurchasesDto;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.model.vo.GameDistributionPresentVO;
import com.ebanma.cloud.game.model.vo.GameEggRuleVO;
import com.ebanma.cloud.game.model.vo.GamePresentRuleVO;
import com.ebanma.cloud.game.model.vo.GamePrizeVO;
import com.ebanma.cloud.game.service.*;
import com.ebanma.cloud.game.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author banma-
 * @version $ Id: GameServiceImpl, v 0.1 2023/06/06 16:32 banma- Exp $
 */
@Slf4j
@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private RedissonClient redissonClient;
    @Resource
    private RedisUtil<String, GameUserInfo> redisUtil;

    @Resource
    private GameUserInfoService gameUserInfoService;

    @Resource
    private GameUserRecordService gameUserRecordService;

    @Resource
    private GameUserPropService gameUserPropService;

    @Resource
    private GameRuleService gameRuleService;

    @Resource
    private TransService transService;




    @Transactional
    @Override
    public GamePrizeVO result(GameDrawDto gameDrawDto) {
        //1.用户锁-避免用户重复提交抽奖
        RLock userLock = redissonClient.getLock(GameRedisEnum.USER_LOCK.getKey()+gameDrawDto.getUserId());
        GameEggRuleVO eggDraw = null;
        try {
            //1.2 加锁
//            if (!userLock.tryLock()) {
//                throw new ServiceException("请勿多次重复点击抽奖");
//            }
            //2.判断用户抽奖次数
            GameUserInfo userInfo = gameUserInfoService.getUserInfo(gameDrawDto.getUserId());
            if ( userInfo.getRemainTimes()<=0){
                log.info("抽奖次数为0");
                throw new ServiceException("抽奖次数为0，请使用积分进行抽奖次数购买");
            }
            //3.获取抽奖概率
            //3.1 Redis中获取抽奖概率控制,
            //3.2 并根据使用的道具,进行概率修正
            List<GameEggRuleVO> gameRules = gameRuleService.getGameRules(userInfo, gameDrawDto.getPropCode());


            //4.金蛋保底控制方法
            eggDraw = gameRuleService.getEggDraw(gameRules);
            boolean goldenEgg = GameEggEnum.GOLDEN_EGG.getEggType().equals(eggDraw.getEggType());
            int guaranteed = gameRuleService.guaranteed(goldenEgg);

            if(guaranteed ==0 && goldenEgg){
                //不出金蛋
                gameRules.remove(gameRuleService.getGoldenEgg(gameRules));
                eggDraw = gameRuleService.getEggDraw(gameRules);
            }else if(guaranteed ==2){
                //出金
                eggDraw = gameRuleService.getGoldenEgg(gameRules);
            }

            //5.根据蛋类型进行奖品抽奖
            GamePresentRuleVO presentDraw = gameRuleService.getPresentDraw(eggDraw.getPresentRuleVOS());

            //6.处理抽奖后数据
            //6.1 统计游戏域各人信息
            userInfo.afterDraw( presentDraw );

            //6.2 更新redis个人信息
            redisUtil.set(GameRedisEnum.USER_INFO.getKey()+userInfo.getUserId(),userInfo, Long.valueOf(GameRedisEnum.USER_INFO_TIME.getKey()), TimeUnit.MINUTES);

            //7.进行红包和积分,道具奖品方法
            asyncDistributionPresent(new GameDistributionPresentVO(userInfo,presentDraw) );


            return new GamePrizeVO(presentDraw);
        }catch (Exception e){
            //清除redis中用户信息
//            redisUtil.remove(GameRedisEnum.USER_INFO.getKey()+gameDrawDto.getUserId());
            //金蛋保底回滚
//            gameRuleService.guaranteedRollback(eggDraw );
            log.info("抽奖时发生错误:"+e.getMessage());
            throw new ServiceException("抽奖时发生错误:"+e.getMessage() ,e);
        }finally {
            //10.用户锁解锁
            if (userLock.isLocked() && userLock.isHeldByCurrentThread()) {
                userLock.unlock();
            }
        }
    }

    @Override
    @Transactional
    public boolean purchases(GamePurchasesDto gamePurchasesDto) {
        RLock lock = redissonClient.getLock(GameRedisEnum.USER_BUY.getKey()+gamePurchasesDto.getUserId());
        try {
            //上锁
            if (!lock.tryLock()) {
                throw new ServiceException("请勿多次重复点击购买");
            }
            if(gamePurchasesDto.getBuyCount()%3 != 0){
                log.info("购买次数应该为3的倍数。");
                throw new ServiceException("购买次数应该为3的倍数。");
            }

            GameUserInfo userInfo = gameUserInfoService.getUserInfo(gamePurchasesDto.getUserId());
            if(transService.updateTrans( gamePurchasesDto.getTransAccountLog())){
                userInfo.setRemainTimes(userInfo.getRemainTimes() + 3);
                redisUtil.set(GameRedisEnum.USER_INFO.getKey()+gamePurchasesDto.getUserId(), userInfo,Long.valueOf(GameRedisEnum.USER_INFO_TIME.getKey()),TimeUnit.MINUTES);
                gameUserInfoService.update(userInfo);
                return true;
            }
        }catch (Exception e) {
            throw new ServiceException("购买时发生错误",e);
        }finally {
            if(lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }

    /**
     * 异步分布呈现
     * 异步发奖
     *
     * @param gameDistributionPresentVO 游戏分布呈现签证官
     */
    private void asyncDistributionPresent(GameDistributionPresentVO gameDistributionPresentVO){

        rocketMQTemplate.convertAndSend(TransRocketMQEnum.TOPIC_TRANS_RECORD.getValue()+":"+TransRocketMQEnum.TAG_TRANS_RECORD.getValue(),
                gameDistributionPresentVO);



//        //1 更新数据库个人数据
//        GameUserInfo userInfo = gameDistributionPresentVO.getGameUserInfo();
//        GamePresentRuleVO presentDraw = gameDistributionPresentVO.getGamePresentRuleVO();
//        //1更新中奖纪录
//        GameUserRecord gameUserRecord = new GameUserRecord(userInfo.getUserId(),"",presentDraw);
//        gameUserRecordService.save(gameUserRecord);
//        gameUserInfoService.update(userInfo);
//        //2.更新道具剩余信息
//        if ( !CollectionUtils.isEmpty(userInfo.getUseGameUserProp())) {
//           userInfo.getUseGameUserProp().forEach( m -> {
//               gameUserPropService.update(m);
//           });
//        }
//        //3 更新redis个人信息
//        redisUtil.set(GameRedisEnum.USER_INFO.getKey()+userInfo.getUserId(),userInfo,Long.valueOf(GameRedisEnum.USER_INFO_TIME.getKey()), TimeUnit.MINUTES);
//        //4. 红包-积分rocketMQ异步发奖
//        if(GamePriceOrPropEnum.RED_PACKET.getValue()==presentDraw.getPresentCode()
//               ||GamePriceOrPropEnum.POINT.getValue()==presentDraw.getPresentCode()  ){
//            log.info("用户:"+userInfo.getUserId()+"  "+ Objects.requireNonNull(GamePriceOrPropEnum.getGamePropByValue(presentDraw.getPresentCode())).getKey() +": "+presentDraw.getPresentCount());
//            rocketMQTemplate.convertAndSend(TransRocketMQEnum.TOPIC_TRANS_RECORD.getValue()+":"+TransRocketMQEnum.TAG_TRANS_RECORD.getValue(),
//                   presentDraw.getTransAccountLog(userInfo.getUserId()));
//        }



    }




}