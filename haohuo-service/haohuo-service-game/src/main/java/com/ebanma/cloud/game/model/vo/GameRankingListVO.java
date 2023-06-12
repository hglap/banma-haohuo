package com.ebanma.cloud.game.model.vo;

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
@ApiModel( value ="GameRankingListVO", description="游戏中奖排行榜list" )
public class GameRankingListVO implements Serializable {


    /**
     * 用户id
     */
    private String userId;
    /**
     * 总参与次数
     */
    private Integer totalDrawTimes;
    /**
     * 中奖次数
     */
    private Integer winningTimes;
    /**
     * 最新中奖日期
     */
    private Date winningDate;


}