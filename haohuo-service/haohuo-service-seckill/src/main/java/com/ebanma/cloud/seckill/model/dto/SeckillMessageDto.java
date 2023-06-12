package com.ebanma.cloud.seckill.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * @author 崔国垲
 * @version $ Id: SeckillMessageDto, v 0.1 2023/06/11 19:22 banma-0197 Exp $
 */
@Data
public class SeckillMessageDto  implements Serializable {
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 抽奖时间
     */
    private Timestamp seckillTime;

    /**
     * 中奖
     */
    private String gitName;

    private int bizType;

    private int amount;

    public SeckillMessageDto() {

    }

    public SeckillMessageDto(Long id, String userId, Long activityId, Timestamp seckillTime, String gitName ) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.seckillTime = seckillTime;
        this.gitName = gitName;


    }

    public SeckillMessageDto(Long id, String userId, Long activityId, Timestamp seckillTime, String gitName, int bizType, int amount) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.seckillTime = seckillTime;
        this.gitName = gitName;
        this.bizType = bizType;
        this.amount = amount;
    }
}
