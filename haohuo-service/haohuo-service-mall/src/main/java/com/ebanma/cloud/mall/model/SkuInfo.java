package com.ebanma.cloud.mall.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "sku_info")
public class SkuInfo {
    /**
     * skuid(itemID)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * sku_id
     */
    @Column(name = "sku_id")
    private String skuId;

    /**
     * 价格
     */
    private Long price;

    /**
     * sku名称
     */
    @Column(name = "sku_name")
    private String skuName;

    /**
     * 商品规格描述
     */
    @Column(name = "sku_desc")
    private String skuDesc;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 分类id（冗余)
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 默认显示图片(冗余)
     */
    @Column(name = "sku_default_img")
    private String skuDefaultImg;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modified_time")
    private Date modifiedTime;

    /**
     * 获取skuid(itemID)
     *
     * @return id - skuid(itemID)
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置skuid(itemID)
     *
     * @param id skuid(itemID)
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取sku_id
     *
     * @return sku_id - sku_id
     */
    public String getSkuId() {
        return skuId;
    }

    /**
     * 设置sku_id
     *
     * @param skuId sku_id
     */
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 获取sku名称
     *
     * @return sku_name - sku名称
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * 设置sku名称
     *
     * @param skuName sku名称
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * 获取商品规格描述
     *
     * @return sku_desc - 商品规格描述
     */
    public String getSkuDesc() {
        return skuDesc;
    }

    /**
     * 设置商品规格描述
     *
     * @param skuDesc 商品规格描述
     */
    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
    }

    /**
     * 获取重量
     *
     * @return weight - 重量
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 设置重量
     *
     * @param weight 重量
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * 获取分类id（冗余)
     *
     * @return category_id - 分类id（冗余)
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类id（冗余)
     *
     * @param categoryId 分类id（冗余)
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取默认显示图片(冗余)
     *
     * @return sku_default_img - 默认显示图片(冗余)
     */
    public String getSkuDefaultImg() {
        return skuDefaultImg;
    }

    /**
     * 设置默认显示图片(冗余)
     *
     * @param skuDefaultImg 默认显示图片(冗余)
     */
    public void setSkuDefaultImg(String skuDefaultImg) {
        this.skuDefaultImg = skuDefaultImg;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return modified_time - 修改时间
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifiedTime 修改时间
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}