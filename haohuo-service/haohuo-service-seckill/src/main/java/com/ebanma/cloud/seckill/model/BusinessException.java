package com.ebanma.cloud.seckill.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 崔国垲
 * @version $ Id: BusinessException, v 0.1 2023/06/11 16:30 banma-0197 Exp $
 */
public class BusinessException extends RuntimeException{
    
    private static final long NO_ERROR_CODE = 1001001000003L;
    
    private final long errorCode;
    public BusinessException(Long errorCode, String message, Throwable throwable,Object[] args){
        super(StringUtils.isEmpty(message) ?String.valueOf(errorCode):message,throwable);
        if(errorCode == null){
            this.errorCode = NO_ERROR_CODE;
        }else{
            this.errorCode = errorCode;
        }
    }
    
    public BusinessException(long errorCode){
        this(errorCode,(String)null,(Throwable)null,(Object[])null);
    }

    public BusinessException(String message){
        super(message);
        this.errorCode = NO_ERROR_CODE;
    }

}
