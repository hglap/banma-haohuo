package com.ebanma.cloud.trans.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "trans_info")
public class TransInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 账户类型：积分、红包
     */
    @Column(name = "trans_type")
    private String transType;

    /**
     * 账户类型码：0001对应积分
     */
    @Column(name = "trans_type_code")
    private String transTypeCode;

    /**
     * 账务ID
     */
    @Column(name = "trans_id")
    private String transId;

    /**
     * 使用状态（1正常 0非正常）
     */
    private String status;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改者
     */
    private String modifier;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取账户类型：积分、红包
     *
     * @return trans_type - 账户类型：积分、红包
     */
    public String getTransType() {
        return transType;
    }

    /**
     * 设置账户类型：积分、红包
     *
     * @param transType 账户类型：积分、红包
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }

    /**
     * 获取账户类型码：0001对应积分
     *
     * @return trans_type_code - 账户类型码：0001对应积分
     */
    public String getTransTypeCode() {
        return transTypeCode;
    }

    /**
     * 设置账户类型码：0001对应积分
     *
     * @param transTypeCode 账户类型码：0001对应积分
     */
    public void setTransTypeCode(String transTypeCode) {
        this.transTypeCode = transTypeCode;
    }

    /**
     * 获取账务ID
     *
     * @return trans_id - 账务ID
     */
    public String getTransId() {
        return transId;
    }

    /**
     * 设置账务ID
     *
     * @param transId 账务ID
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

    /**
     * 获取使用状态（1正常 0非正常）
     *
     * @return status - 使用状态（1正常 0非正常）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置使用状态（1正常 0非正常）
     *
     * @param status 使用状态（1正常 0非正常）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取修改者
     *
     * @return modifier - 修改者
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置修改者
     *
     * @param modifier 修改者
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
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