package com.ebanma.cloud.mall.exception;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @author: why
 * @date: 2023/6/12
 * @time: 9:45
 * @description:
 */
@ControllerAdvice
@Order(500)
public class GlobleExceptionHandler {
    @ExceptionHandler(Throwable.class)
    public ModelAndView throwableHandler(Throwable throwable) {
        throwable.printStackTrace();
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        //格式改成与result一致，否则result改动此处忘记改动将导致bug
//        modelAndView.addObject("code", 500);
//        modelAndView.addObject("message", throwable2String(throwable));
//        modelAndView.addObject("data", null);
//        modelAndView.addObject("success", false);
        Result<Throwable> throwableResult = ResultGenerator.genSuccessResult(throwable);
        modelAndView.addObject("code", 500);
        modelAndView.addObject("message", throwableResult.getMessage());
        System.out.println("信息:"+throwableResult.getMessage());
        modelAndView.addObject("data", null);
        modelAndView.addObject("success", false);


//        modelAndView.addAllObjects(ResultGenerator.genSuccessResult(throwable).toMap());
        return modelAndView;
    }
}
