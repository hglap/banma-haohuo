package com.ebanma.cloud.mall.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class SkuInventoryEditDTO implements Serializable {


    private static final long serialVersionUID = -8238989622259204710L;
    /**
     * 
     */
    private String id;

    /**
     * 商品ID
     */
    private String skuId;

    /**
     * 商家ID
     */
    private String storeId;

    /**
     * 累计入库
     */
    private Long allInQua;

    /**
     * 累计出库
     */
    private Long allOutQua;

    /**
     * 当前库存
     */
    private Long currentQua;

    /**
     * 冻结库存
     */
    private Long freezeQua;

    /**
     * 上下架
     */
    private String useStatus;

    /**
     * 类型 0出库，1入库
     */
    private String type;

    /**
     * 出入库数量
     */
    private Long count;

}