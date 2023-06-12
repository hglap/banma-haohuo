package com.ebanma.cloud.seckill.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author 崔国垲
 * @version $ Id: ActivitySaveDto, v 0.1 2023/06/07 17:39 banma-0197 Exp $
 */
@Data
public class ActivitySaveDto implements Serializable {

    /**
     * 数量
     */
    private long amount;


    /**
     * 创建人id
     */
    private long createUserId;
    /**
     * 创建人姓名
     */
    private String createUserName;
    /**
     * 持续时间
     */
    private int duration;
    /**
     * 日期
     */
    private Date startDate;
    /**
     * 时刻
     */
    private Time startTime;
}
