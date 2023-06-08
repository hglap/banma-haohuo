package com.ebanma.cloud.mall.model.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class SkuDetailVO implements Serializable {
    /**
     * 
     */
    private String id;

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
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String createUser;

    /**
     * 
     */
    private Date lastModified;

    /**
     * 
     */
    private String lastModifyUser;

    /**
     * 删除标记 0未删除 1已删除
     */
    @TableLogic(value = "0", delval = "1")
    private String del;

    /**
     * 
     */
    private Long version;

}