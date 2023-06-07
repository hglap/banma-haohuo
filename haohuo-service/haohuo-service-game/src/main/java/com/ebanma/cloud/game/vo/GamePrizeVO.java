package com.ebanma.cloud.game.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author banma-
 * @version $ Id: GamePrizeVO, v 0.1 2023/06/07 13:05 banma- Exp $
 */
@Data
@ApiModel( value ="GamePrizeVO", description="游戏抽奖奖品" )
public class GamePrizeVO {

    private String presentType;

    private Integer presentCount;

}