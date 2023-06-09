package com.ebanma.cloud.seckill.model.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Seckill {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date seckillTime;

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

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取活动ID
     *
     * @return activity_id - 活动ID
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * 设置活动ID
     *
     * @param activityId 活动ID
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取抽奖时间
     *
     * @return seckill_time - 抽奖时间
     */
    public Date getSeckillTime() {
        return seckillTime;
    }

    /**
     * 设置抽奖时间
     *
     * @param seckillTime 抽奖时间
     */
    public void setSeckillTime(Date seckillTime) {
        this.seckillTime = seckillTime;
    }

    /**
     * 获取中奖类型
     *
     * @return prize_type - 中奖类型
     */
    public Integer getPrizeType() {
        return prizeType;
    }

    /**
     * 设置中奖类型
     *
     * @param prizeType 中奖类型
     */
    public void setPrizeType(Integer prizeType) {
        this.prizeType = prizeType;
    }

    /**
     * 获取中奖金额
     *
     * @return prize_amount - 中奖金额
     */
    public Integer getPrizeAmount() {
        return prizeAmount;
    }

    /**
     * 设置中奖金额
     *
     * @param prizeAmount 中奖金额
     */
    public void setPrizeAmount(Integer prizeAmount) {
        this.prizeAmount = prizeAmount;
    }
}