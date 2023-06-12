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
public enum SkuInventoryTypeEnum {

    IN("1","入库"),
    OUT("0","出库");
    private final String code;
    private final String name;
}
