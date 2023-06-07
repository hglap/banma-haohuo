package com.ebanma.cloud.game.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "game_user_info")
@Data
public class GameUserInfo {
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

}