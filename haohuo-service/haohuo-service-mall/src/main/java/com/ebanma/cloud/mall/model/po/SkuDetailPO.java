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
 * @TableName sku_detail
 * 商品详细
 */
@TableName(value ="sku_detail")
@Data
@Accessors(chain = true)
public class SkuDetailPO extends BasePO implements Serializable {


    /**
     * 商品ID
     */
    private String skuId;

    /**
     * 信息
     */
    private String info;

    /**
     * 内容
     */
    private String content;



    /**
     * 删除标记 0未删除 1已删除
     */
    @TableLogic(value = "0", delval = "1")
    private String del;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}