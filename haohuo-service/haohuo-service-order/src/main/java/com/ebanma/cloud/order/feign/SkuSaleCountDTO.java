package com.ebanma.cloud.order.feign;


public class SkuSaleCountDTO {

    private String skuId;

    private Integer skuSaleCount;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Integer getSkuSaleCount() {
        return skuSaleCount;
    }

    public void setSkuSaleCount(Integer skuSaleCount) {
        this.skuSaleCount = skuSaleCount;
    }
}
