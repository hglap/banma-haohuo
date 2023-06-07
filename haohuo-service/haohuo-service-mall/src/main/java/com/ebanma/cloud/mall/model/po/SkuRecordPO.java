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
 * @TableName sku_record
 * 记录表【收藏等】
 */
@TableName(value ="sku_record")
@Data
public class SkuRecordPO implements Serializable {
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
     * 记录收藏推荐类型
     */
    private String type;

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