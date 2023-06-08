package com.ebanma.cloud.game.model.po;

import com.ebanma.cloud.common.enums.GamePriceOrPropEnum;
import com.ebanma.cloud.game.model.vo.GamePresentRuleVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Table(name = "game_user_info")
@Data
@NoArgsConstructor
public class GameUserInfo implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 剩余抽奖次数
     */
    @Column(name = "remain_times")
    private Integer remainTimes;

    /**
     * 抽奖累积积分
     */
    @Column(name = "total_win_points")
    private Integer totalWinPoints;

    /**
     * 抽奖红包累积
     */
    @Column(name = "total_red_packet")
    private Integer totalRedPacket;

    /**
     * 中奖次数
     */
    @Column(name = "winning_times")
    private Integer winningTimes;

    /**
     * 总抽奖次数
     */
    @Column(name = "total_draw_times")
    private Integer totalDrawTimes;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modified_time")
    private Date modifiedTime;

    List<GameUserProp> gameUserProp;

    GameUserProp useGameUserProp;

    public GameUserInfo(String userId) {
        this.userId = userId;
        this.remainTimes=0;
        this.totalWinPoints=0;
        this.totalRedPacket=0;
        this.winningTimes=0;
        this.totalDrawTimes=0;
        this.createTime = new Date();
        this.modifiedTime = new Date();
    }

    /**
     * 抽奖后个人信息处理
     * 红包和积分认为中奖,统计红包和积分累计,并中奖次数赠一
     * 其他认为为未中奖
     *
     * @param presentDraw 中间类型
     */
    public void afterDraw(GamePresentRuleVO presentDraw ){
        switch (Objects.requireNonNull(GamePriceOrPropEnum.getGamePropByKey(presentDraw.getPresentType()))){
            case RED_PACKET:
                this.totalRedPacket += presentDraw.getPresentCount();
                this.winningTimes ++;
                break;
            case POINT:
                this.totalWinPoints += presentDraw.getPresentCount();
                this.winningTimes ++;
                break;
            default:
                break;
        }
        this.remainTimes --;
        this.totalDrawTimes ++;
        this.modifiedTime = new Date();

    }
}