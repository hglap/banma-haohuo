package com.ebanma.cloud.game.model.vo;

import com.ebanma.cloud.common.util.IdWorker;
import com.ebanma.cloud.game.model.po.GameRule;
import com.ebanma.cloud.trans.api.dto.TransAccountLog;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 概率控制
 * @author banma-
 * @version $ Id: GameEggRuleVO, v 0.1 2023/06/06 22:11 banma- Exp $
 */
@Data
@ApiModel( value ="GamePresentRuleVO", description="抽奖奖品概率控制" )
@NoArgsConstructor
public class GamePresentRuleVO implements Serializable {


    /**
     * 奖品类型
     */
    private Integer presentCode;

    /**
     * 奖品数量
     */
    private Integer presentCount;


    /**
     * 奖品概率
     */
    private Double presentOdd;

    /**
     * 根据游戏规则生成奖品规则
     *
     * @param gameRules 游戏规则
     */
    public GamePresentRuleVO(GameRule gameRules) {
        this.presentCode = gameRules.getPresentCode();
        this.presentCount = gameRules.getPresentCount();
        this.presentOdd = gameRules.getPresentOdd();
    }

    public GamePresentRuleVO(Integer presentType, Integer presentCount, Double presentOdd) {
        this.presentCode = presentType;
        this.presentCount = presentCount;
        this.presentOdd = presentOdd;
    }

    public TransAccountLog getTransAccountLog(String userId) {
        TransAccountLog transAccountLog = new TransAccountLog();
        transAccountLog.setUserId(userId);
        transAccountLog.setBizSerialNumber(String.valueOf(new IdWorker().nextId()));
        transAccountLog.setAmount(this.presentCount);
        transAccountLog.setLogType(0);
        transAccountLog.setBizType(this.presentCode);
        transAccountLog.setDescription("砸蛋活动，发放奖励。");
        transAccountLog.setConsume("game");
        transAccountLog.setCreateOn(new Date());
        transAccountLog.setCreateBy(userId);
        return transAccountLog;
    }
}