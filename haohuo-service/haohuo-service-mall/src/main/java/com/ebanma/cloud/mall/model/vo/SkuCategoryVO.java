package com.ebanma.cloud.mall.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName sku_category
 * 商品分类
 */
@Data
public class SkuCategoryVO implements Serializable {


    private static final long serialVersionUID = -4950907928576586775L;

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





}