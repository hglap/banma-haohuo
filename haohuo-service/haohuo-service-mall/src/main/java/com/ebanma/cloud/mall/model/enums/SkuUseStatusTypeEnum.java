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
public enum SkuUseStatusTypeEnum {

    USE("0","使用中"),
    UN_USE("1","未使用");
    private final String code;
    private final String name;
}
