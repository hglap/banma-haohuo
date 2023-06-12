package com.ebanma.cloud.seckill.model;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 崔国垲
 * @version $ Id: GlobalBussinessExceptionHandler, v 0.1 2023/06/11 16:29 banma-0197 Exp $
 */
@RestControllerAdvice
@Slf4j
public class GlobalBusinessExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public Result handlerBusinessException(BusinessException ex){
        return ResultGenerator.genErrorResult(ResultCode.FAIL.code(),ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result handlerRunTimeException(RuntimeException ex){
        log.error("异常",ex);
        return ResultGenerator.genErrorResult(ResultCode.FAIL.code(),"系统故障");
    }
}
