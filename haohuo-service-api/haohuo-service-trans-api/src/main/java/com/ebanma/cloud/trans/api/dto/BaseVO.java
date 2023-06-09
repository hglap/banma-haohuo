package com.ebanma.cloud.trans.api.dto;


/**
 * @author 鹿胜宝
 * @version $ Id: BaseVO, v 0.1 2023/06/07 19:58 banma-0193 Exp $
 */

public class BaseVO {
    private Integer pageNum = 1;
    private Integer pageSize = 20;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
