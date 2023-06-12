package com.ebanma.cloud.seckill.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author 崔国垲
 * @version $ Id: ActivitySearchInfoVo, v 0.1 2023/06/07 14:28 banma-0197 Exp $
 */
@Data
public class ActivitySearchInfoVo implements Serializable {


    private Long id;

    /**
     * 开始日期
     */
//    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date startDate;

    /**
     * 开始时间
     */
//    @Column(name = "start_time")
    @JsonFormat(pattern = "HH:mm:ss",timezone = "GMT-8")
    private Time startTime;

    /**
     * 持续时间
     */
    private Integer duration;


    /**
     * 创建时间
     */
//    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Timestamp createTime;


    /**
     * 秒杀状态
     */
//    @Column(name = "seckill_status")
    private Integer seckillStatus;


    /**
     * 剩余数量
     */
    private Integer amount;

    /**
     * 创建人名
     */
//    @Column(name = "create_user_name")
    private String createUserName;

}
