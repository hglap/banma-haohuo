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
public enum SkuRecordTypeEnum {

    USER_PRODUCT_COLLECT("用户商品收藏"),
    PRODUCT_KEYWORDS("商品关键词");




    private final String name;
}
