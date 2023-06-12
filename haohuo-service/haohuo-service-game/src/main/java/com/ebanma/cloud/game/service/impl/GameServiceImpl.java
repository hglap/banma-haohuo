package com.ebanma.cloud.game.service.impl;

import com.ebanma.cloud.common.core.ServiceException;
import com.ebanma.cloud.common.enums.GamePriceOrPropEnum;
import com.ebanma.cloud.common.enums.GameRedisEnum;
import com.ebanma.cloud.game.model.dto.GameDrawDto;
import com.ebanma.cloud.game.model.dto.GamePurchasesDto;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.model.vo.GameEggRuleVO;
import com.ebanma.cloud.game.model.vo.GamePresentRuleVO;
import com.ebanma.cloud.game.model.vo.GamePrizeVO;
import com.ebanma.cloud.game.service.*;
import lombok.extern.slf4j.Slf4j;
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
        RLock userLock = redissonClient.getLock(GameRedisEnum.USER_LOCK+gameDrawDto.getUserId());
        GameEggRuleVO eggDraw = null;
        try {
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
            eggDraw = gameRuleService.getEggDrawByGuaranteed(gameRules);

            //5.根据蛋类型进行奖品抽奖
            GamePresentRuleVO presentDraw = gameRuleService.getPresentDraw(eggDraw.getPresentRuleVOS());

            //6.处理抽奖后数据
            //6.1 统计游戏域各人信息
            userInfo.afterDraw( presentDraw );
            //6.2 抽奖记录
            GameUserRecord gameUserRecord = new GameUserRecord(gameDrawDto.getUserId(),"",presentDraw);
            gameUserRecordService.save(gameUserRecord);
            //6.3 更新redis内用户个人数据
            redisTemplate.opsForValue().set(GameRedisEnum.USER_INFO.getKey()+gameDrawDto.getUserId(),userInfo,5, TimeUnit.MINUTES);

            //7.异步进行红包和积分,道具奖品方法
            asyncDistributionPresent(userInfo,presentDraw);
            return new GamePrizeVO(presentDraw);
        }catch (Exception e){
            //清楚redis中用户信息
            redisTemplate.delete(GameRedisEnum.USER_INFO.getKey()+gameDrawDto.getUserId());
            //金蛋保底回滚
            gameRuleService.guaranteedRollback(eggDraw);
            GameUserInfo userInfo = redisTemplate.opsForValue().get(GameRedisEnum.DRAW_INFO.getKey());
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

       private void asyncDistributionPresent(GameUserInfo userInfo, GamePresentRuleVO presentDraw){
//           ThreadUtil.execAsync( new Runnable() {
//               @Override
//               public void run() {
                   //1. 更新数据库信息
                   //更新游戏域各人信息
                   gameUserInfoService.update(userInfo);
                   //更新道具剩余信息
                   if ( !CollectionUtils.isEmpty(userInfo.getUseGameUserProp())) {
                       userInfo.getUseGameUserProp().forEach( m -> {
                           gameUserPropService.update(m);
                       });
                   }
                   //2. 红包-积分调用接口
                   switch (Objects.requireNonNull( GamePriceOrPropEnum.getGamePropByValue(presentDraw.getPresentCode()))){
                       case RED_PACKET:
                           log.info("用户:"+userInfo.getUserId()+"  红包: "+presentDraw.getPresentCount());
                           transService.updateTrans(presentDraw.getTransAccountLog(userInfo.getUserId()));
                           break;
                       case POINT:
                           log.info("用户:"+userInfo.getUserId()+"  积分: "+presentDraw.getPresentCount());
                           transService.updateTrans(presentDraw.getTransAccountLog(userInfo.getUserId()));
                           break;
                       default:
                           break;
                   }

//               }
//           });



       }

}