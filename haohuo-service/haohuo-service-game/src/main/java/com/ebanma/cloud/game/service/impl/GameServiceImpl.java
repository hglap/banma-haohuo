package com.ebanma.cloud.game.service.impl;

import com.ebanma.cloud.common.core.ServiceException;
import com.ebanma.cloud.common.enums.GamePriceOrPropEnum;
import com.ebanma.cloud.common.enums.GameRedisEnum;
import com.ebanma.cloud.common.enums.TransRocketMQEnum;
import com.ebanma.cloud.game.model.dto.GameDrawDto;
import com.ebanma.cloud.game.model.dto.GamePurchasesDto;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.model.vo.GameEggRuleVO;
import com.ebanma.cloud.game.model.vo.GamePresentRuleVO;
import com.ebanma.cloud.game.model.vo.GamePrizeVO;
import com.ebanma.cloud.game.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    private RedisTemplate<String, GameUserInfo> redisTemplate;

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
//        Date date = new Date();
        try {
            //1.2 加锁
            userLock.lock();
            //2.判断用户抽奖次数
            GameUserInfo userInfo = gameUserInfoService.getUserInfo(gameDrawDto.getUserId());
            if ( userInfo.getRemainTimes()<=0){
                log.info("抽奖次数为0");
                throw new ServiceException("抽奖次数为0，请使用积分进行抽奖次数购买");
            }
//            System.out.println("2-耗时： "+(System.currentTimeMillis()-date.getTime()));
            //3.获取抽奖概率
            //3.1 Redis中获取抽奖概率控制,
            //3.2 并根据使用的道具,进行概率修正
            List<GameEggRuleVO> gameRules = gameRuleService.getGameRules(userInfo, gameDrawDto.getPropCode());

//            System.out.println("3-耗时： "+(System.currentTimeMillis()-date.getTime()));

            //4.金蛋保底控制方法
            eggDraw = gameRuleService.getEggDrawByGuaranteed(gameRules,gameDrawDto.getUserId());

//            System.out.println("4-耗时： "+(System.currentTimeMillis()-date.getTime()));

            //5.根据蛋类型进行奖品抽奖
            GamePresentRuleVO presentDraw = gameRuleService.getPresentDraw(eggDraw.getPresentRuleVOS());

//            System.out.println("5-耗时： "+(System.currentTimeMillis()-date.getTime()));

            //6.处理抽奖后数据
            //6.1 统计游戏域各人信息
            userInfo.afterDraw( presentDraw );

//            System.out.println("6.1-耗时： "+(System.currentTimeMillis()-date.getTime()));
            //6.2 抽奖记录
            GameUserRecord gameUserRecord = new GameUserRecord(gameDrawDto.getUserId(),"",presentDraw);
            gameUserRecordService.save(gameUserRecord);

//            System.out.println("6.2-耗时： "+(System.currentTimeMillis()-date.getTime()));

            //7.进行红包和积分,道具奖品方法
            asyncDistributionPresent(userInfo,presentDraw);

//            System.out.println("7-耗时： "+(System.currentTimeMillis()-date.getTime()));

            return new GamePrizeVO(presentDraw);
        }catch (Exception e){
            //清除redis中用户信息
            redisTemplate.delete(GameRedisEnum.USER_INFO.getKey()+gameDrawDto.getUserId());
            //金蛋保底回滚
            gameRuleService.guaranteedRollback(eggDraw );
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
            lock.lock();
            if(gamePurchasesDto.getBuyCount()%3 != 0){
                log.info("购买次数应该为3的倍数。");
                throw new ServiceException("购买次数应该为3的倍数。");
            }

            GameUserInfo userInfo = gameUserInfoService.getUserInfo(gamePurchasesDto.getUserId());
            if(transService.updateTrans( gamePurchasesDto.getTransAccountLog())){
                userInfo.setRemainTimes(userInfo.getRemainTimes() + 3);
                redisTemplate.opsForValue().set(GameRedisEnum.USER_INFO.getKey()+gamePurchasesDto.getUserId(), userInfo,5,TimeUnit.MINUTES);
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
     * 异步发奖
     *
     * @param userInfo    用户信息
     * @param presentDraw 现在画
     */
    private void asyncDistributionPresent(GameUserInfo userInfo, GamePresentRuleVO presentDraw){
//        Date date = new Date();
        //1 更新数据库个人数据
        gameUserInfoService.update(userInfo);
//        System.out.println("7.1-耗时： "+(System.currentTimeMillis()-date.getTime()));
        //2.更新道具剩余信息
        if ( !CollectionUtils.isEmpty(userInfo.getUseGameUserProp())) {
           userInfo.getUseGameUserProp().forEach( m -> {
               gameUserPropService.update(m);
           });
        }
//        System.out.println("7.2-耗时： "+(System.currentTimeMillis()-date.getTime()));
        //3 更新redis个人信息
        redisTemplate.opsForValue().set(GameRedisEnum.USER_INFO.getKey()+userInfo.getUserId(),userInfo,5, TimeUnit.MINUTES);
//        System.out.println("7.3-耗时： "+(System.currentTimeMillis()-date.getTime()));
        //4. 红包-积分rocketMQ异步发奖
        if(GamePriceOrPropEnum.RED_PACKET.getValue()==presentDraw.getPresentCode()
               ||GamePriceOrPropEnum.POINT.getValue()==presentDraw.getPresentCode()  ){
            log.info("用户:"+userInfo.getUserId()+"  "+ Objects.requireNonNull(GamePriceOrPropEnum.getGamePropByValue(presentDraw.getPresentCode())).getKey() +": "+presentDraw.getPresentCount());
            rocketMQTemplate.convertAndSend(TransRocketMQEnum.TOPIC_TRANS_RECORD.getValue()+":"+TransRocketMQEnum.TAG_TRANS_RECORD.getValue(),
                   presentDraw.getTransAccountLog(userInfo.getUserId()));
        }
//        System.out.println("7.4-耗时： "+(System.currentTimeMillis()-date.getTime()));

//        //2. 红包-积分调用接口--同步发奖
//               switch (Objects.requireNonNull( GamePriceOrPropEnum.getGamePropByValue(presentDraw.getPresentCode()))){
//                   case RED_PACKET:
//                       log.info("用户:"+userInfo.getUserId()+"  红包: "+presentDraw.getPresentCount());
//                       transService.updateTrans(presentDraw.getTransAccountLog(userInfo.getUserId()));
//                       break;
//                   case POINT:
//                       log.info("用户:"+userInfo.getUserId()+"  积分: "+presentDraw.getPresentCount());
//                       transService.updateTrans(presentDraw.getTransAccountLog(userInfo.getUserId()));
//                       break;
//                   default:
//                       break;
//               }


    }

}