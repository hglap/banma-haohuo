package com.ebanma.cloud.mall.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sku_store_info
 * 商家信息
 */
@TableName(value ="sku_store_info")
@Data
public class SkuStoreInfoPO implements Serializable {
    /**
     * 
     */
    @TableId
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
     * 
     */
    private Long version;

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
     * 删除标记 0未删除 1已删除
     */
    @TableLogic(value = "0", delval = "1")
    private String del;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}