package com.ebanma.cloud.trans.api.dto;

import com.github.pagehelper.PageInfo;

/**
 * @author 鹿胜宝
 * @version $ Id: TransAccountLogVO, v 0.1 2023/06/07 20:21 banma-0193 Exp $
 */
public class TransAccountLogVO {
    /**
     * 账户Id
     */
    private String transId;
    /**
     * 总积分
     */
    private Long amountPoints;
    /**
     * 明细列表
     */
    private PageInfo<TransAccountLogDTO> logList;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Long getAmountPoints() {
        return amountPoints;
    }

    public void setAmountPoints(Long amountPoints) {
        this.amountPoints = amountPoints;
    }

    public PageInfo<TransAccountLogDTO> getLogList() {
        return logList;
    }

    public void setLogList(PageInfo<TransAccountLogDTO> logList) {
        this.logList = logList;
    }
}
