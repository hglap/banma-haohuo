package com.ebanma.cloud.game.api.vo;

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
    private Integer presentCode;

    /**
     * 奖品数量
     */
    private Integer presentCount;


    /**
     * 奖品概率
     */
    private Double presentOdd;


    public GamePresentRuleVO(Integer presentType, Integer presentCount, Double presentOdd) {
        this.presentCode = presentType;
        this.presentCount = presentCount;
        this.presentOdd = presentOdd;
    }
}