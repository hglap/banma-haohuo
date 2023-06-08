package com.ebanma.cloud.trans.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RedPacket {
    /**
     * 主键
     */

    private Integer id;

    /**
     * 红包id
     */

    private String redPacketId;

    /**
     * 状态 0 未使用；1已使用 2已过期
     */
    private Integer status;

    /**
     * 红包金额
     */

    private Integer redPacketAmount;

    /**
     * 过期时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expireTime;

    /**
     * 创建时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createOn;

    /**
     * 创建人
     */

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
     * 获取状态 0 未使用；1已使用 2已过期
     *
     * @return status - 状态 0 未使用；1已使用 2已过期
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0 未使用；1已使用 2已过期
     *
     * @param status 状态 0 未使用；1已使用 2已过期
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取红包金额
     *
     * @return red_packet_amount - 红包金额
     */
    public Integer getRedPacketAmount() {
        return redPacketAmount;
    }

    /**
     * 设置红包金额
     *
     * @param redPacketAmount 红包金额
     */
    public void setRedPacketAmount(Integer redPacketAmount) {
        this.redPacketAmount = redPacketAmount;
    }

    /**
     * 获取过期时间
     *
     * @return expire_time - 过期时间
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置过期时间
     *
     * @param expireTime 过期时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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