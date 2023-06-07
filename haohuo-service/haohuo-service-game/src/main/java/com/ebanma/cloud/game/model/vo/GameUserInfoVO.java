package com.ebanma.cloud.game.model.vo;

import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.po.GameUserProp;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 *
 * @author banma-
 * @version $ Id: GameUserInfoVO, v 0.1 2023/06/07 11:15 banma- Exp $
 */
@Data
@ApiModel( value ="GameUserInfoVO", description="用户游戏信息" )
public class GameUserInfoVO {


    /**
     * 个人积分
     */
    private Integer userPoints;
    /**
     * 剩余砸锤次数
     */
    private Integer remainTimes;
    /**
     * 获取积分总数
     */
    private Integer totalWinPoints;
    /**
     * 获取红包总数
     */
    private Integer totalRedPacket;

    /**
     * 用户道具
     */
    private List<GameUserProp> gameUserProp;

    public GameUserInfoVO(GameUserInfo gameUserInfo) {
        this.remainTimes = gameUserInfo.getRemainTimes();
        this.totalWinPoints = gameUserInfo.getTotalWinPoints();
        this.totalRedPacket = gameUserInfo.getTotalRedPacket();
    }
}