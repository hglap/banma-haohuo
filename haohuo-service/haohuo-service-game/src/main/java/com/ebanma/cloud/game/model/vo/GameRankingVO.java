package com.ebanma.cloud.game.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author banma-
 * @version $ Id: GameUserInfoVO, v 0.1 2023/06/07 11:15 banma- Exp $
 */
@Data
@ApiModel( value ="GameRankingVO", description="游戏中奖排行榜" )
public class GameRankingVO implements Serializable {


    /**
     * 总参与次数
     */
    private Integer totalPlayTimes;
    /**
     * 总参与人数
     */
    private Integer totalPlayerCount;

    /**
     * 用户道具
     */
    private List<GameRankingListVO> rankingList ;



}