package com.ebanma.cloud.order.feign;

import java.util.List;

public class SkuInfoQueryDTO {

    private List<String> skuIDList;

    public List<String> getSkuIDList() {
        return skuIDList;
    }

    public void setSkuIDList(List<String> skuIDList) {
        this.skuIDList = skuIDList;
    }
}
