package com.ebanma.cloud.trans.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "trans_order")
public class TransOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 交易类型 0增加 1支出
     */
    @Column(name = "log_type")
    private Integer logType;

    /**
     * 代币类型 0积分 1红包
     */
    @Column(name = "biz_type")
    private Integer bizType;

    /**
     * 订单状态 0下单 1完成
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 业务流水号，幂等用
     */
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * 创建时间
     */
    @Column(name = "create_on")
    private Date createOn;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 获取交易类型 0增加 1支出
     *
     * @return log_type - 交易类型 0增加 1支出
     */
    public Integer getLogType() {
        return logType;
    }

    /**
     * 设置交易类型 0增加 1支出
     *
     * @param logType 交易类型 0增加 1支出
     */
    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    /**
     * 获取代币类型 0积分 1红包
     *
     * @return biz_type - 代币类型 0积分 1红包
     */
    public Integer getBizType() {
        return bizType;
    }

    /**
     * 设置代币类型 0积分 1红包
     *
     * @param bizType 代币类型 0积分 1红包
     */
    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    /**
     * 获取订单状态 0下单 1完成
     *
     * @return order_status - 订单状态 0下单 1完成
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态 0下单 1完成
     *
     * @param orderStatus 订单状态 0下单 1完成
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取业务流水号，幂等用
     *
     * @return serialNumber - 业务流水号，幂等用
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置业务流水号，幂等用
     *
     * @param serialnumber 业务流水号，幂等用
     */
    public void setSerialNumber(String serialnumber) {
        this.serialNumber = serialnumber;
    }

    /**
     * 获取创建时间
     *
     * @return create_on - 创建时间
     */
    public Date getCreateOn() {
        return createOn;
    }

    /**
     * 设置创建时间
     *
     * @param createOn 创建时间
     */
    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}