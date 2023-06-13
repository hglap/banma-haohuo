package com.ebanma.cloud.mall.model.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName sku_category
 * 商品分类
 */
@Data
public class SkuCategoryInsertDTO implements Serializable {


    private static final long serialVersionUID = 7950653630275683129L;
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