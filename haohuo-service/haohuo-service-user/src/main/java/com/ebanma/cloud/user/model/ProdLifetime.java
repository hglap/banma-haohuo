package com.ebanma.cloud.user.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "prod_lifetime")
public class ProdLifetime {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 主体ID
     */
    @Column(name = "principal_id")
    private String principalId;

    /**
     * 主体类型
     */
    @Column(name = "principal_type")
    private String principalType;

    /**
     * 产品码
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 累计金额
     */
    private Long amount;

    /**
     * 累计次数
     */
    private Long count;

    /**
     * 反向累计金额
     */
    @Column(name = "writeoff_amount")
    private Long writeoffAmount;

    /**
     * 反向累计次数
     */
    @Column(name = "writeoff_count")
    private Long writeoffCount;

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
     * 获取主体ID
     *
     * @return principal_id - 主体ID
     */
    public String getPrincipalId() {
        return principalId;
    }

    /**
     * 设置主体ID
     *
     * @param principalId 主体ID
     */
    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    /**
     * 获取主体类型
     *
     * @return principal_type - 主体类型
     */
    public String getPrincipalType() {
        return principalType;
    }

    /**
     * 设置主体类型
     *
     * @param principalType 主体类型
     */
    public void setPrincipalType(String principalType) {
        this.principalType = principalType;
    }

    /**
     * 获取产品码
     *
     * @return product_code - 产品码
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品码
     *
     * @param productCode 产品码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取累计金额
     *
     * @return amount - 累计金额
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * 设置累计金额
     *
     * @param amount 累计金额
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * 获取累计次数
     *
     * @return count - 累计次数
     */
    public Long getCount() {
        return count;
    }

    /**
     * 设置累计次数
     *
     * @param count 累计次数
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * 获取反向累计金额
     *
     * @return writeoff_amount - 反向累计金额
     */
    public Long getWriteoffAmount() {
        return writeoffAmount;
    }

    /**
     * 设置反向累计金额
     *
     * @param writeoffAmount 反向累计金额
     */
    public void setWriteoffAmount(Long writeoffAmount) {
        this.writeoffAmount = writeoffAmount;
    }

    /**
     * 获取反向累计次数
     *
     * @return writeoff_count - 反向累计次数
     */
    public Long getWriteoffCount() {
        return writeoffCount;
    }

    /**
     * 设置反向累计次数
     *
     * @param writeoffCount 反向累计次数
     */
    public void setWriteoffCount(Long writeoffCount) {
        this.writeoffCount = writeoffCount;
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