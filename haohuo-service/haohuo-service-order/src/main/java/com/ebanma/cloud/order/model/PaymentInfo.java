package com.ebanma.cloud.order.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "payment_info")
public class PaymentInfo {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对外业务编号
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 订单编号
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 支付宝交易流水编号
     */
    @Column(name = "alipay_trade_no")
    private String alipayTradeNo;

    /**
     * 支付金额
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 交易内容
     */
    private String subject;

    /**
     * 支付方式
     */
    @Column(name = "payment_type")
    private String paymentType;

    /**
     * 支付时间
     */
    @Column(name = "payment_time")
    private Date paymentTime;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取对外业务编号
     *
     * @return out_trade_no - 对外业务编号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置对外业务编号
     *
     * @param outTradeNo 对外业务编号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
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
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取支付宝交易流水编号
     *
     * @return alipay_trade_no - 支付宝交易流水编号
     */
    public String getAlipayTradeNo() {
        return alipayTradeNo;
    }

    /**
     * 设置支付宝交易流水编号
     *
     * @param alipayTradeNo 支付宝交易流水编号
     */
    public void setAlipayTradeNo(String alipayTradeNo) {
        this.alipayTradeNo = alipayTradeNo;
    }

    /**
     * 获取支付金额
     *
     * @return total_amount - 支付金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置支付金额
     *
     * @param totalAmount 支付金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取交易内容
     *
     * @return subject - 交易内容
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置交易内容
     *
     * @param subject 交易内容
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取支付方式
     *
     * @return payment_type - 支付方式
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * 设置支付方式
     *
     * @param paymentType 支付方式
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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
}