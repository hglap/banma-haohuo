package com.ebanma.cloud.order.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.ebanma.cloud.common.dto.Page;
import com.ebanma.cloud.mall.api.vo.SkuInfoVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfoDTO extends Page {

    private int pageNum;

    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 主键
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 付款方式
     */
    private String paymentWay;

    /**
     * 送货地址
     */
    private String deliveryAddress;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 收件人电话
     */
    private String consigneeTel;

    /**
     * 订单备注
     */
    private String orderComment;

    /**
     * 订单交易编号（第三方支付用)
     */
    private String outTradeNo;

    /**
     * 订单描述(第三方支付用)
     */
    private String tradeBody;

    private SkuInfoVO skuInfoVO;

    public SkuInfoVO getSkuInfoVO() {
        return skuInfoVO;
    }

    public void setSkuInfoVO(SkuInfoVO skuInfoVO) {
        this.skuInfoVO = skuInfoVO;
    }

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 支付时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date paymentTime;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryTime;

    /**
     * 签收时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date consignTime;

    /**
     * 图片路径
     */
    private String imgUrl;

    /**
     * 商品id
     */
    private String skuId;

    /**
     * 商品数量
     */
    private Integer skuNum;

    /**
     * 抵扣积分
     */
    private String integral;

    /**
     * 红包id
     */
    private String redPacketId;

    /**
     * 购买价格(下单时sku价格）
     */
    private BigDecimal orderPrice;

    /**
     * 商户id
     */
    private String merchantId;

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
     * 获取总金额
     *
     * @return total_amount - 总金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置总金额
     *
     * @param totalAmount 总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取订单状态
     *
     * @return order_status - 订单状态
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态
     *
     * @param orderStatus 订单状态
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取付款方式
     *
     * @return payment_way - 付款方式
     */
    public String getPaymentWay() {
        return paymentWay;
    }

    /**
     * 设置付款方式
     *
     * @param paymentWay 付款方式
     */
    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    /**
     * 获取送货地址
     *
     * @return delivery_address - 送货地址
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * 设置送货地址
     *
     * @param deliveryAddress 送货地址
     */
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * 获取收货人
     *
     * @return consignee - 收货人
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置收货人
     *
     * @param consignee 收货人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 获取收件人电话
     *
     * @return consignee_tel - 收件人电话
     */
    public String getConsigneeTel() {
        return consigneeTel;
    }

    /**
     * 设置收件人电话
     *
     * @param consigneeTel 收件人电话
     */
    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    /**
     * 获取订单备注
     *
     * @return order_comment - 订单备注
     */
    public String getOrderComment() {
        return orderComment;
    }

    /**
     * 设置订单备注
     *
     * @param orderComment 订单备注
     */
    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    /**
     * 获取订单交易编号（第三方支付用)
     *
     * @return out_trade_no - 订单交易编号（第三方支付用)
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置订单交易编号（第三方支付用)
     *
     * @param outTradeNo 订单交易编号（第三方支付用)
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * 获取订单描述(第三方支付用)
     *
     * @return trade_body - 订单描述(第三方支付用)
     */
    public String getTradeBody() {
        return tradeBody;
    }

    /**
     * 设置订单描述(第三方支付用)
     *
     * @param tradeBody 订单描述(第三方支付用)
     */
    public void setTradeBody(String tradeBody) {
        this.tradeBody = tradeBody;
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
     * 获取支付时间
     *
     * @return payment_time - 支付时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置支付时间
     *
     * @param paymentTime 支付时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取发货时间
     *
     * @return delivery_time - 发货时间
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 设置发货时间
     *
     * @param deliveryTime 发货时间
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 获取签收时间
     *
     * @return consign_time - 签收时间
     */
    public Date getConsignTime() {
        return consignTime;
    }

    /**
     * 设置签收时间
     *
     * @param consignTime 签收时间
     */
    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    /**
     * 获取图片路径
     *
     * @return img_url - 图片路径
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片路径
     *
     * @param imgUrl 图片路径
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Integer getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(String redPacketId) {
        this.redPacketId = redPacketId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

}