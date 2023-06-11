package com.ebanma.cloud.seckill.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author 崔国垲
 * @version $ Id: ActivityGetInfoVo, v 0.1 2023/06/11 17:07 banma-0197 Exp $
 */
@Data
public class ActivityGetInfoVo {
    private long activityId;
    private int amount;
    private int duration;
    private String path;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
    private LocalDate startDate;
    @JsonFormat(pattern = "HH:mm:ss",timezone = "GMT-8")
    private LocalTime startTime;

}
