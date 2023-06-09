package com.ebanma.cloud.mall.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName sku_attachment
 */
@Data
public class SkuAttachmentInsertDTO implements Serializable {


    private static final long serialVersionUID = -6893077402605232060L;
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

    private String lastModifyUser;



}