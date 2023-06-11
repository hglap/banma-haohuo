package com.ebanma.cloud.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 鹿胜宝
 * @version $ Id: TransAccountLogVO, v 0.1 2023/06/07 20:21 banma-0193 Exp $
 */
@Data
public class TransAccountLogDTO {
    /**
     * 代币类型 0积分 1红包
     */
    private Integer bizType;
    /**
     * 红包状态，0 未使用； 1 已使用； 2已过期 ；3 7日内过期
     */
    private Integer redPacketStatus;
    /**
     * 红包过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date redPacketExpireTime;

    /**
     * 交易类型 0增加 1支出
     */
    private Integer logType;
    /**
     * 流水值
     */
    private Integer amount;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createOn;

    /**
     * 实抵金额
     */
    private BigDecimal actualAmount;

}
