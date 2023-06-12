package com.ebanma.cloud.mall.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 15:35
 * @description:
 */
@Data
public class SkuInfoVO {
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
     * 已卖数量
     */
    private Integer saledCount;

    /**
     * 热度
     */
    private Integer heat;

    /**
     * 推荐总数
     */
    private Integer recommandCount;

    /**
     * 是否收藏
     */
    private Boolean collect;

    /**
     * 照片列表
     */
    private List<SkuAttachmentVO> attachmentVOList;

    /**
     * 商家名称
     */
    private String storeName;

}
