package com.ebanma.cloud.game.vo;

import com.ebanma.cloud.game.model.GameRule;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

/**
 * 概率控制
 * @author banma-
 * @version $ Id: GameEggRuleVO, v 0.1 2023/06/06 22:11 banma- Exp $
 */
@Data
@ApiModel( value ="GameEggRuleVO", description="抽奖蛋概率控制" )
public class GameEggRuleVO {


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
        gameRules.forEach( m -> {
            this.presentRuleVOS.add( new GamePresentRuleVO(m));
        });
    }
}