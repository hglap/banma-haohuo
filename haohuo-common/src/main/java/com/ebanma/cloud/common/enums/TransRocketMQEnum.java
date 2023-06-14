package com.ebanma.cloud.common.enums;

/**
 * 账务域消息队列枚举
 *
 * @author banma-
 * @date 2023/06/07
 */
public enum TransRocketMQEnum {

    TRANS_GROUP("trans-group"),
    TOPIC_TRANS_RECORD("trans-record-topic"),
    TAG_TRANS_RECORD("trans-record-tag");

    private String value;

    TransRocketMQEnum(String value) {
        this.value = value;
    }

    public static TransRocketMQEnum getEnum(String value) {
        for (TransRocketMQEnum rocketMQEnum : TransRocketMQEnum.values()) {
            if (rocketMQEnum.getValue().equals(value) ) {
                return rocketMQEnum;
            }
        }
        return null;
    }

    public  String getValue() {
        return value;
    }

    public  void setValue(String value) {
        this.value = value;
    }
}

