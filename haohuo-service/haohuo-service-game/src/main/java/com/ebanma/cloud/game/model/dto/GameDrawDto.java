package com.ebanma.cloud.game.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author banma-
 * @version $ Id: GameDrawDto, v 0.1 2023/06/07 11:24 banma- Exp $
 */
@Data
@ApiModel( value ="GameDrawDto", description="抽奖入参" )
public class GameDrawDto {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 道具类型
     */
    private int propType;
}