package com.ebanma.cloud.mall.model.dto;

import com.ebanma.cloud.mall.model.vo.BasePageSearchVO;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName sku_category
 * 商品分类
 */
@Data
public class SkuCategorySearchDTO extends BasePageSearchVO implements Serializable {

    /**
     * 名称
     */
    private String name;

    /**
     * 编号
     */
    private String categoryNo;

    /**
     * 状态
     */
    private String useStatus;







}