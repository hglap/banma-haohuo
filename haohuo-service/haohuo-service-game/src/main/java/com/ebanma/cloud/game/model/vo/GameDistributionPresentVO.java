package com.ebanma.cloud.game.model.vo;

import com.ebanma.cloud.game.model.po.GameUserInfo;
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
@ApiModel( value ="GameDistributionPresentVO", description="游戏抽奖奖品发放" )
public class GameDistributionPresentVO implements Serializable {

    private GameUserInfo gameUserInfo;

    private GamePresentRuleVO gamePresentRuleVO;

    public GameDistributionPresentVO(GameUserInfo gameUserInfo, GamePresentRuleVO gamePresentRuleVO) {
        this.gameUserInfo = gameUserInfo;
        this.gamePresentRuleVO = gamePresentRuleVO;
    }
}