package com.ebanma.cloud.mall.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName sku_inventory
 * 商品库存表
 */
@TableName(value ="sku_inventory")
@Data
public class SkuInventoryPO implements Serializable {
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
     * 
     */
    private String del;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}