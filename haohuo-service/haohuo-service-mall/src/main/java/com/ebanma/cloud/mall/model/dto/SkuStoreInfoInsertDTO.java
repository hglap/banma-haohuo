package com.ebanma.cloud.mall.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName sku_store_info
 * 商家信息
 */
@Data
public class SkuStoreInfoInsertDTO implements Serializable {

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

}