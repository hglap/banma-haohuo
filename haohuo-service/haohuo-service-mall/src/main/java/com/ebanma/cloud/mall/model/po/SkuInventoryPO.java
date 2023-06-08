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
 * @TableName sku_inventory
 * 商品库存表
 */
@TableName(value ="sku_inventory")
@Data
@Accessors(chain = true)
public class SkuInventoryPO extends BasePO implements Serializable {

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



    /**
     * 删除标记 0未删除 1已删除
     */
    @TableLogic(value = "0", delval = "1")
    private String del;

    private static final long serialVersionUID = 4411727871776847658L;


}