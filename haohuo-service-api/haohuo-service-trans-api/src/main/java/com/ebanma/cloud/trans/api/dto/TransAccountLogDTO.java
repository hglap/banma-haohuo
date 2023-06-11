package com.ebanma.cloud.trans.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 鹿胜宝
 * @version $ Id: TransAccountLogVO, v 0.1 2023/06/07 20:21 banma-0193 Exp $
 */

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

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getRedPacketStatus() {
        return redPacketStatus;
    }

    public void setRedPacketStatus(Integer redPacketStatus) {
        this.redPacketStatus = redPacketStatus;
    }

    public Date getRedPacketExpireTime() {
        return redPacketExpireTime;
    }

    public void setRedPacketExpireTime(Date redPacketExpireTime) {
        this.redPacketExpireTime = redPacketExpireTime;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }
}
