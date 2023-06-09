package com.ebanma.cloud.game.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import com.ebanma.cloud.common.core.ServiceException;
import com.ebanma.cloud.common.enums.GamePriceOrPropEnum;
import com.ebanma.cloud.common.enums.GameRedisEnum;
import com.ebanma.cloud.game.model.dto.GameDrawDto;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.model.vo.GameEggRuleVO;
import com.ebanma.cloud.game.model.vo.GamePresentRuleVO;
import com.ebanma.cloud.game.model.vo.GamePrizeVO;
import com.ebanma.cloud.game.service.*;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Autowired
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


    @Transactional
    @Override
    public GamePrizeVO result(GameDrawDto gameDrawDto) {
        //1.用户锁-避免用户重复提交抽奖
        RLock userLock = redissonClient.getLock(GameRedisEnum.USER_LOCK+gameDrawDto.getUserId());

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
            List<GameEggRuleVO> gameRules = gameRuleService.getGameRules(userInfo, gameDrawDto.getPropType());

            //4.金蛋保底控制方法
            GameEggRuleVO eggDraw = gameRuleService.getEggDraw(gameRules);

            //5.根据蛋类型进行奖品抽奖
            GamePresentRuleVO presentDraw = gameRuleService.getPresentDraw(eggDraw.getPresentRuleVOS());

            //6.处理抽奖后数据
            //6.1 统计游戏域各人信息
            userInfo.afterDraw( presentDraw );
            //6.2 更新数据库信息
            //更新游戏域各人信息
            gameUserInfoService.update(userInfo);
            //更新道具剩余信息
            gameUserPropService.update(userInfo.getUseGameUserProp());
            //清除道具使用记录
            userInfo.setUseGameUserProp(null);
            //6.3 抽奖记录
            GameUserRecord gameUserRecord = new GameUserRecord(gameDrawDto.getUserId(),presentDraw);
            gameUserRecordService.save(gameUserRecord);
            //6.4 更新redis内用户个人数据
            redisTemplate.opsForValue().set(GameRedisEnum.USER_INFO.getKey()+gameDrawDto.getUserId(),userInfo,5, TimeUnit.MINUTES);

            //7.异步进行奖品方法
            ThreadUtil.execAsync( new Runnable() {
                @Override
                public void run() {
                    switch (Objects.requireNonNull(GamePriceOrPropEnum.getGamePropByKey(presentDraw.getPresentType()))){
                        case RED_PACKET:
                            System.out.println(GamePriceOrPropEnum.RED_PACKET.getKey()+GamePriceOrPropEnum.RED_PACKET.getValue()+presentDraw.getPresentCount());
                            break;
                        case POINT:
                            System.out.println(GamePriceOrPropEnum.POINT.getKey()+GamePriceOrPropEnum.POINT.getValue()+presentDraw.getPresentCount());
                            break;
                        case A:break;
                        case B:break;
                        case C:break;
                        case LUCKY_HAMMER:break;
                        case ANGEL_BLESSINGS:break;
                        default:
                            break;
                    }
                    //调用账务域接口进行红包发放

                }
            });
            return new GamePrizeVO(presentDraw);
        }catch (ServiceException e){
            throw e;
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
//            throw new ServiceException("用户抽奖时发生异常", e );
        }finally {
            //10.用户锁解锁
            if (userLock.isLocked() && userLock.isHeldByCurrentThread()) {
                userLock.unlock();
            }
        }
    }



}