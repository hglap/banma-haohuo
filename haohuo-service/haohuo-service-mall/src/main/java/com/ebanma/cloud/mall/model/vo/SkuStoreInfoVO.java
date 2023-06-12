package com.ebanma.cloud.mall.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @TableName sku_store_info
 * 商家信息
 */
@Data
@Accessors(chain = true)
public class SkuStoreInfoVO implements Serializable {

    private static final long serialVersionUID = -3322758266892160421L;
    /**
     * 
     */
    private String id;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 账户
     */
    private String account;

    /**
     * 电话
     */
    private String tel;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 用户状态
     */
    private String useStatus;

    /**
     * 营业金额
     */
    private BigDecimal income;
    /**
     * 订单数量
     */
    private Integer orderCount;
    /**
     * 商品数量
     */
    private Long productCount;


}