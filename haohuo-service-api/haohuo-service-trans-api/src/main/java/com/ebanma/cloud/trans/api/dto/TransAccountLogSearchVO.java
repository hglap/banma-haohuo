package com.ebanma.cloud.trans.api.dto;


import java.io.Serializable;

/**
 * @author 鹿胜宝
 * @version $ Id: TransAccountLogSearchVO, v 0.1 2023/06/07 19:57 banma-0193 Exp $
 */

public class TransAccountLogSearchVO implements Serializable {
    //用户id
    private String userId;

    //流水类型 0获取 1消耗
    private Integer logType;

    //代币类型 0积分 1红包
    private Integer bizType;

    //红包状态 0未使用 1已使用 2已过期 3七日内过期
    private Integer redPacketStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

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
}
