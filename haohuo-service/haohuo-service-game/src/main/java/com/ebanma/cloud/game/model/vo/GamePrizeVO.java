package com.ebanma.cloud.game.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author banma-
 * @version $ Id: GamePrizeVO, v 0.1 2023/06/07 13:05 banma- Exp $
 */
@Data
@NoArgsConstructor
@ApiModel( value ="GamePrizeVO", description="游戏抽奖奖品" )
public class GamePrizeVO implements Serializable {

    private String presentType;

    private Integer presentCount;

    public GamePrizeVO(GamePresentRuleVO gamePresentRuleVO) {
        this.presentType = gamePresentRuleVO.getPresentType();
        this.presentCount = gamePresentRuleVO.getPresentCount();
    }
}