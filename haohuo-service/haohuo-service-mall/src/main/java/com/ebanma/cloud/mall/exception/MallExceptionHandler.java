package com.ebanma.cloud.mall.exception;

import com.ebanma.cloud.common.exception.MallException;
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
@Order(400)
public class MallExceptionHandler {
    @ExceptionHandler(MallException.class)
    public ModelAndView throwableHandler(MallException exception) {
        exception.printStackTrace();
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("code", 500);
        modelAndView.addObject("message", exception.getLocalizedMessage());
        modelAndView.addObject("data", null);
        modelAndView.addObject("success", false);
        return modelAndView;
    }

    private static String throwable2String(Throwable throwable) {
        //lb2022-10-10，集团安全要求，后端往前端返回的信息里面不能有后端异常的堆栈，否则容易造成安全泄露，因此改成只返回异常的原始信息，不返回堆栈
//        try {
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            throwable.printStackTrace(pw);
//            sw.close();
//            pw.close();
//            return "\r\n" + sw + "\r\n";
//        } catch (Exception e2) {
//            return "ErrorInfoFromException";
//        }
        String errMsg = "" ;
        if (throwable != null) {
            errMsg = throwable.toString() + "\r\n" ;
            StackTraceElement[]  stackTraceElements = throwable.getStackTrace() ;
            if (stackTraceElements != null) {
                //只打印前三行堆栈
                for (int i = 0 ; i < Math.min(1,stackTraceElements.length) ; i ++) {
                    errMsg = errMsg + stackTraceElements[i].toString() + "\r\n" ;
                }
            }
        }
        return errMsg ;

    }
}
