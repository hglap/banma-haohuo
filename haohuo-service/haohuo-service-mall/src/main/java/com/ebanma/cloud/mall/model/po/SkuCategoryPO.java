package com.ebanma.cloud.mall.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sku_category
 * 商品分类
 */
@TableName(value ="sku_category")
@Data
public class SkuCategoryPO implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编号
     */
    private String categoryNo;

    /**
     * 导航栏是否显示(0显示 1不显示)
     */
    private String useStatus;

    /**
     * 排序
     */
    private Long seq;

    /**
     * 描述
     */
    private String description;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", categoryNo=").append(categoryNo);
        sb.append(", useStatus=").append(useStatus);
        sb.append(", seq=").append(seq);
        sb.append(", desc=").append(description);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", lastModified=").append(lastModified);
        sb.append(", lastModifyUser=").append(lastModifyUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}