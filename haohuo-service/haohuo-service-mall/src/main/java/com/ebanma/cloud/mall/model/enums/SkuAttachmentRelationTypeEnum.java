package com.ebanma.cloud.mall.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 17:38
 * @description:
 */

@Getter
@AllArgsConstructor
public enum SkuAttachmentRelationTypeEnum {

    PRODUCT_ALBUM("商品相册"),
    PRODUCT_DETAIL_IMAGE("商品详情图片");



    private final String name;
}
