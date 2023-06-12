package com.ebanma.cloud.seckill.model.enumUtil;

/**
 * @author 崔国垲
 * @version $ Id: ActivityEnum, v 0.1 2023/06/11 15:58 banma-0197 Exp $
 */
public enum ActivityEnum {

    DONE(0,"已完成"),
    UNPUBLISHED(1,"待发布"),
    DOING(2,"进行中");

    private final Integer code;
    private final String message;
    ActivityEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
