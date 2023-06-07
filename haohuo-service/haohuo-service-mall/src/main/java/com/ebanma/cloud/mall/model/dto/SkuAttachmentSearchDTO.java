package com.ebanma.cloud.mall.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


@Data
@Accessors(chain = true)
public class SkuAttachmentSearchDTO implements Serializable {
    /**
     * id
     */

    private String id;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 地址
     */
    private String url;

    /**
     * 排序
     */
    private String seq;

    /**
     * 关联id
     */
    private String relationId;


    /**
     * 关联类型
     */
    private String relationType;

    /**
     * 附件大小
     */
    private String size;

    /**
     *  关联ID列表
     */
    private List<String> relationIdList;


}