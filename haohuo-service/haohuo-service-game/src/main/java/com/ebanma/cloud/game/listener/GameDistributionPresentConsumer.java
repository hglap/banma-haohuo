package com.ebanma.cloud.game.listener;

import com.ebanma.cloud.common.core.ServiceException;
import com.ebanma.cloud.common.enums.GamePriceOrPropEnum;
import com.ebanma.cloud.common.enums.GameRedisEnum;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.model.vo.GameDistributionPresentVO;
import com.ebanma.cloud.game.model.vo.GamePresentRuleVO;
import com.ebanma.cloud.game.service.GameUserInfoService;
import com.ebanma.cloud.game.service.GameUserPropService;
import com.ebanma.cloud.game.service.GameUserRecordService;
import com.ebanma.cloud.game.service.TransService;
import com.ebanma.cloud.game.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author banma-hanshuaiaho
 * @version $ Id: GameDistributionPresentConsumer, v 0.1 2023/06/13 8:48 banma- Exp $
 */
@Slf4j
@Component
@RocketMQMessageListener( topic = "trans-record-topic",selectorExpression = "trans-record-tag",consumerGroup = "trans-group" )
public class GameDistributionPresentConsumer implements RocketMQListener<GameDistributionPresentVO> {

    @Resource
    private TransService transService;

    @Resource
    private GameUserInfoService gameUserInfoService;

    @Resource
    private GameUserPropService gameUserPropService;

    @Resource
    private RedisUtil<String, GameUserInfo> redisUtil;

    @Resource
    private GameUserRecordService gameUserRecordService;


    @Override
    public void onMessage(GameDistributionPresentVO gameDistributionPresentVO) {
        try {
            GameUserInfo userInfo = gameDistributionPresentVO.getGameUserInfo();
            GamePresentRuleVO presentDraw = gameDistributionPresentVO.getGamePresentRuleVO();

            //1 抽奖记录
            GameUserRecord gameUserRecord = new GameUserRecord(userInfo.getUserId(),"",presentDraw);
            gameUserRecordService.save(gameUserRecord);
            //2 更新数据库个人数据

            gameUserInfoService.update(userInfo);
            //3.更新道具剩余信息
            if ( !CollectionUtils.isEmpty(userInfo.getUseGameUserProp())) {
                userInfo.getUseGameUserProp().forEach( m -> {
                    gameUserPropService.update(m);
                });
            }
            //4. 红包-积分feign发奖
            if(GamePriceOrPropEnum.RED_PACKET.getValue()==presentDraw.getPresentCode()
                    ||GamePriceOrPropEnum.POINT.getValue()==presentDraw.getPresentCode()  ){
                log.info("用户:"+userInfo.getUserId()+"  "+ Objects.requireNonNull(GamePriceOrPropEnum.getGamePropByValue(presentDraw.getPresentCode())).getKey() +": "+presentDraw.getPresentCount());
                transService.updateTrans( presentDraw.getTransAccountLog(userInfo.getUserId()));
            }
        } catch (Exception e) {
            throw new ServiceException("发奖时发生异常:"+e.getMessage(),e);
        }
    }
}