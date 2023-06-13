package com.ebanma.cloud.order.feign;


import java.math.BigDecimal;

public class countDTO {

    private String skuId;

    private Integer skuSaleCount;

    private String merchantId;

    private Integer orderCount;

    private BigDecimal amountCount;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getAmountCount() {
        return amountCount;
    }

    public void setAmountCount(BigDecimal amountCount) {
        this.amountCount = amountCount;
    }

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
