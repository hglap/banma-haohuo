package com.ebanma.cloud.mall.api.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 15:35
 * @description:
 */
public class SkuInfoVO {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuDesc() {
        return skuDesc;
    }

    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSkuDefaultImg() {
        return skuDefaultImg;
    }

    public void setSkuDefaultImg(String skuDefaultImg) {
        this.skuDefaultImg = skuDefaultImg;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getMaxUsePoints() {
        return maxUsePoints;
    }

    public void setMaxUsePoints(String maxUsePoints) {
        this.maxUsePoints = maxUsePoints;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public Long getCurrentQua() {
        return currentQua;
    }

    public void setCurrentQua(Long currentQua) {
        this.currentQua = currentQua;
    }

    public Integer getSaledCount() {
        return saledCount;
    }

    public void setSaledCount(Integer saledCount) {
        this.saledCount = saledCount;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public Integer getRecommandCount() {
        return recommandCount;
    }

    public void setRecommandCount(Integer recommandCount) {
        this.recommandCount = recommandCount;
    }

    public Boolean getCollect() {
        return collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }

    public List<SkuAttachmentVO> getAttachmentVOList() {
        return attachmentVOList;
    }

    public void setAttachmentVOList(List<SkuAttachmentVO> attachmentVOList) {
        this.attachmentVOList = attachmentVOList;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     *
     */
    private String id;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品描述
     */
    private String skuDesc;

    /**
     * 商家ID
     */
    private String storeId;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 默认图片
     */
    private String skuDefaultImg;

    /**
     * 商品编号
     */
    private String skuNo;

    /**
     * 商品货号
     */
    private String goodsNo;

    /**
     * 最大使用积分
     */
    private String maxUsePoints;

    /**
     * 是否上架(0:上架 1:下架)
     */
    private String useStatus;

    /**
     * 当前库存
     */
    private Long currentQua;


    /**
     * 已卖数量
     */
    private Integer saledCount;

    /**
     * 热度
     */
    private Integer heat;

    /**
     * 推荐总数
     */
    private Integer recommandCount;

    /**
     * 是否收藏
     */
    private Boolean collect;

    /**
     * 照片列表
     */
    private List<SkuAttachmentVO> attachmentVOList;

    /**
     * 商家名称
     */
    private String storeName;

}
