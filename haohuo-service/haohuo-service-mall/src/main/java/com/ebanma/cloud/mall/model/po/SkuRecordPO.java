package com.ebanma.cloud.mall.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName sku_record
 * 记录表【收藏等】
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sku_record")
@Data
@Accessors(chain = true)
public class SkuRecordPO extends BasePO implements Serializable {


    /**
     * 商品ID
     */
    private String skuId;

    /**
     * 记录收藏推荐类型
     */
    private String type;



    /**
     * 删除标记 0未删除 1已删除
     */
    @TableLogic(value = "0", delval = "1")
    private String del;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}