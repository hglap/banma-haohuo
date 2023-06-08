package com.ebanma.cloud.mall.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName sku_detail
 * 商品详细
 */
@Data
@Accessors(chain = true)
public class SkuDetailInsertDTO implements Serializable {


    private static final long serialVersionUID = 6182413181937996026L;

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



}