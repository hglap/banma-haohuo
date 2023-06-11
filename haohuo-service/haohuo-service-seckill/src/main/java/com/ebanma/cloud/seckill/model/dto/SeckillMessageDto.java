package com.ebanma.cloud.seckill.model.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author 崔国垲
 * @version $ Id: SeckillMessageDto, v 0.1 2023/06/11 19:22 banma-0197 Exp $
 */
@Data
public class SeckillMessageDto {
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
    private Date seckillTime;

    /**
     * 中奖
     */
    private String gitName;

    public SeckillMessageDto() {

    }

    public SeckillMessageDto(Long id, String userId, Long activityId, Date seckillTime, String gitName ) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.seckillTime = seckillTime;
        this.gitName = gitName;
    }
}
