package com.ebanma.cloud.seckill.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class Seckill {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 活动ID
     */
    @Column(name = "activity_id")
    private Long activityId;

    /**
     * 抽奖时间
     */
    @Column(name = "seckill_time")
    private Timestamp seckillTime;

    /**
     * 中奖类型
     */
    @Column(name = "prize_type")
    private Integer prizeType;

    /**
     * 中奖金额
     */
    @Column(name = "prize_amount")
    private Integer prizeAmount;


}