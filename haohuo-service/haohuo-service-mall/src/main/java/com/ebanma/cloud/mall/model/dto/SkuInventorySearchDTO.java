package com.ebanma.cloud.mall.model.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ebanma.cloud.mall.model.po.BasePO;
import com.ebanma.cloud.mall.model.vo.BasePageSearchVO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @TableName sku_inventory
 * 商品库存表
 */
@Data
@Accessors(chain = true)
public class SkuInventorySearchDTO extends BasePageSearchVO implements Serializable {


    private static final long serialVersionUID = 9216679308682410447L;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品货号
     */
    private String goodsNo;

    /**
     * 库存区间
     */
    private List<Long> currentQua;



}