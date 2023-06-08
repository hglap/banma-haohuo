package com.ebanma.cloud.mall.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class SkuInventoryInsertDTO implements Serializable {


    private static final long serialVersionUID = -8238989622259204710L;
    /**
     * 
     */
    private String id;

    /**
     * 商品ID
     */
    private String skuId;

    /**
     * 商家ID
     */
    private String storeId;

    /**
     * 累计入库
     */
    private Long allInQua;

    /**
     * 累计出库
     */
    private Long allOutQua;

    /**
     * 当前库存
     */
    private Long currentQua;

    /**
     * 冻结库存
     */
    private Long freezeQua;

    /**
     * 上下架
     */
    private String useStatus;


}