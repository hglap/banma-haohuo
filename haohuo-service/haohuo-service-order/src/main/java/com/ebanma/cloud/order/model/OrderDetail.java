package com.ebanma.cloud.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "order_detail")
public class OrderDetail {
    /**
     * 主键
     */
    @TableId(value = "id", type= IdType.ASSIGN_ID)
    private Long id;

    /**
     * 订单编号
     */
    @TableField(value = "order_id")
    private String orderId;

    /**
     * sku_id
     */
    @TableField(value = "sku_id")
    private String skuId;

    /**
     * sku名称（冗余)
     */
    @TableField(value = "sku_name")
    private String skuName;

    /**
     * 图片地址（冗余)
     */
    @TableField(value = "sku_img")
    private String skuImg;

    /**
     * 购买价格(下单时sku价格）
     */
    @TableField(value = "order_price")
    private BigDecimal orderPrice;

    /**
     * 购买个数
     */
    @TableField(value = "sku_num")
    private String skuNum;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_id - 订单编号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编号
     *
     * @param orderId 订单编号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
     * 获取sku名称（冗余)
     *
     * @return sku_name - sku名称（冗余)
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * 设置sku名称（冗余)
     *
     * @param skuName sku名称（冗余)
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * 获取图片地址（冗余)
     *
     * @return sku_img - 图片地址（冗余)
     */
    public String getSkuImg() {
        return skuImg;
    }

    /**
     * 设置图片地址（冗余)
     *
     * @param skuImg 图片地址（冗余)
     */
    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg;
    }

    /**
     * 获取购买价格(下单时sku价格）
     *
     * @return order_price - 购买价格(下单时sku价格）
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * 设置购买价格(下单时sku价格）
     *
     * @param orderPrice 购买价格(下单时sku价格）
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * 获取购买个数
     *
     * @return sku_num - 购买个数
     */
    public String getSkuNum() {
        return skuNum;
    }

    /**
     * 设置购买个数
     *
     * @param skuNum 购买个数
     */
    public void setSkuNum(String skuNum) {
        this.skuNum = skuNum;
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
}