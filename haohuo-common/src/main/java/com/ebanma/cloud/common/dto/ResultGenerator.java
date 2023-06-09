package com.ebanma.cloud.common.dto;

import com.ebanma.cloud.common.enums.ResultCode;
import org.springframework.util.StringUtils;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";

    public static Result genSuccessResult() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.code());
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }

    public static Result genSuccessResult(String message) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.code());
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result()
                .setCode(ResultCode.SUCCESS.code())
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        Result result = new Result();
        result.setCode(ResultCode.FAIL.code());
        if (!StringUtils.hasText(message)) {
            result.setMessage(DEFAULT_FAIL_MESSAGE);
        } else {
            result.setMessage(message);
        }
        return result;
    }

    public static Result genUnRegisterResult(String message) {
        Result result = new Result();
        result.setCode(ResultCode.UNREGISTER.code());
        if (!StringUtils.hasText(message)) {
            result.setMessage(DEFAULT_FAIL_MESSAGE);
        } else {
            result.setMessage(message);
        }
        return result;
    }

    public static Result genFirstLoginResult(String message,String data) {
        Result result = new Result();
        result.setCode(ResultCode.UNREGISTER.code());
        if (!StringUtils.hasText(message)) {
            result.setMessage("First Time Login");
        } else {
            result.setMessage(message);
        }
        result.setData(data);
        return result;
    }

    public static Result genErrorResult(ResultCode code, String message) {
        Result result = new Result();
        result.setCode(code.code());
        result.setMessage(message);
        return result;
    }
}
