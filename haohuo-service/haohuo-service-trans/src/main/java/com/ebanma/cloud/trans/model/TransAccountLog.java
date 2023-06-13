package com.ebanma.cloud.trans.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "trans_account_log")
public class TransAccountLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Transient
    private String userId;

    /**
     * 账务id
     */
    @Column(name = "trans_id")
    private String transId;

    /**
     * 流水值
     */
    @Column(name = "amount")
    private Integer amount;

    /**
     * 交易类型 0增加 1支出
     */
    @Column(name = "log_type")
    @NotNull(message = "交易类型不能为空")
    private Integer logType;

    /**
     * 代币类型 0积分 1红包
     */
    @Column(name = "biz_type")
    @NotNull(message = "代币类型不能为空")
    private Integer bizType;

    /**
     * 实抵金额
     */
    @Column(name = "actual_amount")
    private BigDecimal actualAmount;

    /**
     * 描述
     */
    @NotNull(message = "账务流水描述不能为空")
    private String description;

    /**
     * 来源
     */
    private String source;

    /**
     * 用途
     */
    private String consume;

    /**
     * 红包id
     */
    @Column(name = "red_packet_id")
    private String redPacketId;

    /**
     * 创建时间
     */
    @Column(name = "create_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createOn;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 红包实体
     */
    private RedPacket redPacket;

    /**
     * 业务流水号
     */
    @Transient
    @NotBlank(message = "业务流水号不能为空")
    private String bizSerialNumber;

    public String getBizSerialNumber() {
        return bizSerialNumber;
    }

    public void setBizSerialNumber(String bizSerialNumber) {
        this.bizSerialNumber = bizSerialNumber;
    }

    public RedPacket getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(RedPacket redPacket) {
        this.redPacket = redPacket;
    }

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
     * @return userId - 用户id
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
     * 获取账务id
     *
     * @return trans_id - 账务id
     */
    public String getTransId() {
        return transId;
    }

    /**
     * 设置账务id
     *
     * @param transId 账务id
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

    /**
     * 获取流水值
     *
     * @return amount - 流水值
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置流水值
     *
     * @param amount 流水值
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
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
     * 获取实抵金额
     *
     * @return actual_amount - 实抵金额
     */
    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    /**
     * 设置实抵金额
     *
     * @param actualAmount 实抵金额
     */
    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取来源
     *
     * @return source - 来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置来源
     *
     * @param source 来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取用途
     *
     * @return consume - 用途
     */
    public String getConsume() {
        return consume;
    }

    /**
     * 设置用途
     *
     * @param consume 用途
     */
    public void setConsume(String consume) {
        this.consume = consume;
    }

    /**
     * 获取红包id
     *
     * @return red_packet_id - 红包id
     */
    public String getRedPacketId() {
        return redPacketId;
    }

    /**
     * 设置红包id
     *
     * @param redPacketId 红包id
     */
    public void setRedPacketId(String redPacketId) {
        this.redPacketId = redPacketId;
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