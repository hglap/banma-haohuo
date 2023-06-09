package com.ebanma.cloud.trans.model;

import javax.persistence.*;

@Table(name = "trans_red_packet")
public class TransRedPacket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账户id
     */
    @Column(name = "trans_id")
    private String transId;

    /**
     * 红包id
     */
    @Column(name = "red_packet_id")
    private String redPacketId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取账户id
     *
     * @return trans_id - 账户id
     */
    public String getTransId() {
        return transId;
    }

    /**
     * 设置账户id
     *
     * @param transId 账户id
     */
    public void setTransId(String transId) {
        this.transId = transId;
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
}