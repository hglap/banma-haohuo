package com.ebanma.cloud.mall.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName sku_attachment
 */
@TableName(value ="sku_attachment")
@Accessors(chain = true)
@Data
public class SkuAttachmentPO extends BasePO implements Serializable {


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
     * 删除标记 0未删除 1已删除
     */
    @TableLogic(value = "0", delval = "1")
    private String del;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}