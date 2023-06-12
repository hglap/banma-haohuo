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
public class SkuCategoryPO extends BasePO implements Serializable {


    private static final long serialVersionUID = -7331111886455644727L;
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


}