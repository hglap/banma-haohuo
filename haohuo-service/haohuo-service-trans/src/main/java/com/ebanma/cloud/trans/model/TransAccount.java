package com.ebanma.cloud.trans.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "trans_account")
public class TransAccount {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账务ID
     */
    @Column(name = "trans_id")
    private String transId;

    /**
     * 初始余额。普通用户为0，总账账户为10000000
     */
    @Column(name = "init_amount")
    private Long initAmount;

    /**
     * 当前余额
     */
    private Long balance;

    /**
     * 当前冻结余额
     */
    @Column(name = "freeze_Balance")
    private Long freezeBalance;

    /**
     * 当前余额
     */
    @Column(name = "total_in_amount")
    private Long totalInAmount;

    /**
     * 总流出金额
     */
    @Column(name = "total_out_amount")
    private Long totalOutAmount;

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
     * 获取初始余额。普通用户为0，总账账户为10000000
     *
     * @return init_amount - 初始余额。普通用户为0，总账账户为10000000
     */
    public Long getInitAmount() {
        return initAmount;
    }

    /**
     * 设置初始余额。普通用户为0，总账账户为10000000
     *
     * @param initAmount 初始余额。普通用户为0，总账账户为10000000
     */
    public void setInitAmount(Long initAmount) {
        this.initAmount = initAmount;
    }

    /**
     * 获取当前余额
     *
     * @return balance - 当前余额
     */
    public Long getBalance() {
        return balance;
    }

    /**
     * 设置当前余额
     *
     * @param balance 当前余额
     */
    public void setBalance(Long balance) {
        this.balance = balance;
    }

    /**
     * 获取当前冻结余额
     *
     * @return freeze_Balance - 当前冻结余额
     */
    public Long getFreezeBalance() {
        return freezeBalance;
    }

    /**
     * 设置当前冻结余额
     *
     * @param freezeBalance 当前冻结余额
     */
    public void setFreezeBalance(Long freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    /**
     * 获取当前余额
     *
     * @return total_in_amount - 当前余额
     */
    public Long getTotalInAmount() {
        return totalInAmount;
    }

    /**
     * 设置当前余额
     *
     * @param totalInAmount 当前余额
     */
    public void setTotalInAmount(Long totalInAmount) {
        this.totalInAmount = totalInAmount;
    }

    /**
     * 获取总流出金额
     *
     * @return total_out_amount - 总流出金额
     */
    public Long getTotalOutAmount() {
        return totalOutAmount;
    }

    /**
     * 设置总流出金额
     *
     * @param totalOutAmount 总流出金额
     */
    public void setTotalOutAmount(Long totalOutAmount) {
        this.totalOutAmount = totalOutAmount;
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