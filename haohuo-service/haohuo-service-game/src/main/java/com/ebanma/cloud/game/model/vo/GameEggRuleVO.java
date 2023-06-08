package com.ebanma.cloud.game.model.vo;

import com.ebanma.cloud.game.model.po.GameRule;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 概率控制
 * @author banma-
 * @version $ Id: GameEggRuleVO, v 0.1 2023/06/06 22:11 banma- Exp $
 */
@Data
@ApiModel( value ="GameEggRuleVO", description="抽奖蛋概率控制" )
@NoArgsConstructor
public class GameEggRuleVO implements Serializable {


    /**
     * 蛋类型
     */
    private String eggType;

    /**
     * 蛋概率
     */
    private Double eggOdd;

    /**
     * 奖品集合
     */
    private List<GamePresentRuleVO> presentRuleVOS;

    public GameEggRuleVO(List<GameRule> gameRules ) {
        this.eggType = gameRules.get(0).getEggType();
        this.eggOdd = gameRules.get(0).getEggOdd();
        this.presentRuleVOS = new ArrayList<>();
        gameRules.forEach( m -> {
            this.presentRuleVOS.add( new GamePresentRuleVO(m));
        });
    }
}