package com.ebanma.cloud.game.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author banma-
 * @version $ Id: GameDrawDto, v 0.1 2023/06/07 11:24 banma- Exp $
 */
@Data
@ApiModel( value ="GameUserRecordDto", description="抽奖参与记录入参" )
public class GameUserRecordDto  {

    /**
     * 用户id
     */
    private String userId;

    //当前页码
    private int pageNum;

    //每页条数
    private int pageSize;


}