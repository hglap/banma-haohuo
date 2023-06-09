package com.ebanma.cloud.seckill.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date startTime;

    /**
     * 持续时间
     */
    private Integer duration;

    /**
     * 金蛋数量
     */
    private Integer stock;

    /**
     * 创建时间
     */
//    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date createTime;

    /**
     * 创建人
     */
//    @Column(name = "create_user_id")
    private String createUserId;

    /**
     * 秒杀状态
     */
//    @Column(name = "seckill_status")
    private Integer seckillStatus;

    /**
     * 版本
     */
    private Integer version;

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