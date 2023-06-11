package com.ebanma.cloud.mall.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 15:35
 * @description:
 */
@Data
public class SkuInventoryVO {
    /**
     *
     */
    private String id;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 商品名称
     */
    private String skuName;

    private String goodsNo;

    private String skuNo;

    private Long currentQua;

    private List<SkuAttachmentVO> attachmentVOList;


}
