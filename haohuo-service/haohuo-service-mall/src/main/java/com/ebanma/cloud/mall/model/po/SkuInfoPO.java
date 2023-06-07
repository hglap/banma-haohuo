package com.ebanma.cloud.mall.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sku_info
 * 商品信息
 */
@TableName(value ="sku_info")
@Data
public class SkuInfoPO implements Serializable {
    /**
     * 
     */
    @TableId
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
     * 
     */
    private String version;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String createUser;

    /**
     * 
     */
    private Date lastModified;

    /**
     * 
     */
    private String lastModifyUser;

    /**
     * 是否删除(0:使用 1:删除)
     */
    private String del;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}