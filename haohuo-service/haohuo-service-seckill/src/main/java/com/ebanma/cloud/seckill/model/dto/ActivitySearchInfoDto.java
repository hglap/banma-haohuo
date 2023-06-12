package com.ebanma.cloud.seckill.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 崔国垲
 * @version $ Id: ActivitySearchInfoDto, v 0.1 2023/06/07 14:01 banma-0197 Exp $
 */
@Data
public class ActivitySearchInfoDto extends BaseDto {

    private String createUserName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Timestamp createTime;

}
