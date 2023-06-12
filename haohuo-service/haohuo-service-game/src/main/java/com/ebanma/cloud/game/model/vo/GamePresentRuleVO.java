package com.ebanma.cloud.game.model.vo;

import com.ebanma.cloud.game.model.po.GameRule;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
    private String presentType;

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
        this.presentType = gameRules.getPresentType();
        this.presentCount = gameRules.getPresentCount();
        this.presentOdd = gameRules.getPresentOdd();
    }

    public GamePresentRuleVO(String presentType, Integer presentCount, Double presentOdd) {
        this.presentType = presentType;
        this.presentCount = presentCount;
        this.presentOdd = presentOdd;
    }
}