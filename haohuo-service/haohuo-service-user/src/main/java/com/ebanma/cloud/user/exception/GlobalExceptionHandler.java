package com.ebanma.cloud.user.exception;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.common.exception.MallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 鹿胜宝
 * @version $ Id: GlobalExceptionHandler, v 0.1 2023/06/12 13:34 banma-0193 Exp $
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MallException.class)
    public Result businessExceptionHandler(MallException e) {
        return ResultGenerator.genFailResult(e.getMessage());
    }
}
