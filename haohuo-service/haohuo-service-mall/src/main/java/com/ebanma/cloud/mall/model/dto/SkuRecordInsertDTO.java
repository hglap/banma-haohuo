package com.ebanma.cloud.mall.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ebanma.cloud.mall.model.po.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 
 * @TableName sku_record
 * 记录表【收藏等】
 */
@Data
@Accessors(chain = true)
public class SkuRecordInsertDTO extends BasePO implements Serializable {


    private static final long serialVersionUID = 7474903291496418244L;
    /**
     * 商品ID
     */
    private String skuId;

    /**
     * 记录收藏推荐类型
     */
    private String type;


}