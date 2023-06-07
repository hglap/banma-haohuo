package com.ebanma.cloud.mall.model.dto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;



@Data
@Accessors(chain = true)
public class SkuRecordSearchDTO implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 商品ID
     */
    private String skuId;

    /**
     * 记录收藏推荐类型
     */
    private String type;

    /**
     * 
     */
    private Long version;

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
    private String del;

    /**
     * 用户ID
     */
    private String userId;
}