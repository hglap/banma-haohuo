package com.ebanma.cloud.mall.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName sku_detail
 * 商品详细
 */
@TableName(value ="sku_detail")
@Data
public class SkuDetailPO implements Serializable {
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
     * 信息
     */
    private String info;

    /**
     * 内容
     */
    private String content;

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
    private String lastModified;

    /**
     * 
     */
    private String lastModifyUser;

    /**
     * 
     */
    private String del;

    /**
     * 
     */
    private Long version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}