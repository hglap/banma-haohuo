package com.ebanma.cloud.order.feign;

import java.util.List;

public class SkuInfoQueryDTO {

    private List<String> skuIDList;

    private List<String> merchantIdList;

    public List<String> getMerchantIdList() {
        return merchantIdList;
    }

    public void setMerchantIdList(List<String> merchantIdList) {
        this.merchantIdList = merchantIdList;
    }

    public List<String> getSkuIDList() {
        return skuIDList;
    }

    public void setSkuIDList(List<String> skuIDList) {
        this.skuIDList = skuIDList;
    }
}
