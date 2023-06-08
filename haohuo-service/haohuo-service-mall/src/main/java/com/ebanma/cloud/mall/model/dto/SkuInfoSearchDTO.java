package com.ebanma.cloud.mall.model.dto;

import com.ebanma.cloud.mall.model.vo.BasePageSearchVO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 15:31
 * @description:
 */
@Data
public class SkuInfoSearchDTO extends BasePageSearchVO implements Serializable {


    private static final long serialVersionUID = -2302063942924722335L;
    // 分类
    private String category;
    // 搜索值
    private String searchValue;
    // 商品ID
    private String productId;
    // 商品名称
    private String skuName;
    // 商品分类ID
    private String categoryId;
    // 商品货号
    private String goodsNo;

}
