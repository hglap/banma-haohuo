package com.ebanma.cloud.game.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author banma-
 * @version $ Id: GameUserInfoVO, v 0.1 2023/06/07 11:15 banma- Exp $
 */
@Data
@ApiModel( value ="GameTopRankVO", description="中奖滚动信息" )
public class GameTopRankVO implements Serializable {


    /**
     * 用户id
     */
    private String userId;
    /**
     * 名字
     */
    private String userName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;



}