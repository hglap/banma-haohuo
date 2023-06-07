package com.ebanma.cloud.mall.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sku_attachment
 */
@TableName(value ="sku_attachment")
@Data
public class SkuAttachmentPO implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 地址
     */
    private String url;

    /**
     * 排序
     */
    private String seq;

    /**
     * 关联id
     */
    private String relationId;

    /**
     * 附件大小
     */
    private String size;

    /**
     * 关联类型
     */
    private String relationType;

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
    private String lastModified;

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