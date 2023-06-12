package com.ebanma.cloud.mall.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @TableName sku_info
 * 商品信息
 */

@Data
@Accessors(chain = true)
public class SkuInfoEditDTO implements Serializable {


    private static final long serialVersionUID = -4842189898294781021L;
    /**
     * 
     */
    private String id;

    /**
     * 商品ID
     */
    private String skuId;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品描述
     */
    private String skuDesc;

    /**
     * 商家ID
     */
    private String storeId;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 默认图片
     */
    private String skuDefaultImg;

    /**
     * 商品编号
     */
    private String skuNo;

    /**
     * 商品货号
     */
    private String goodsNo;

    /**
     * 最大使用积分
     */
    private String maxUsePoints;

    /**
     * 是否上架(0:上架 1:下架)
     */
    private String useStatus;

    /**
     * 当前库存
     */
    private Long currentQua;


    /**
     * 关键词
     */
    private String keyWordsList;

    /**
     * 商品相册
     */
    private List<SkuAttachmentInsertDTO> attachmentInfoList;


    private List<SkuDetailInsertDTO> skuDetailDTO;

    /**
     * 商品详情
     */
    private SkuAttachmentInsertDTO detailPhoto;

}