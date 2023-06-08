package com.ebanma.cloud.mall.api.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class SkuAttachmentVO implements Serializable {
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
     * 附件大小
     */
    private String size;


}