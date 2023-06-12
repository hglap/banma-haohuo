package com.ebanma.cloud.user.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.util.JwtUtil;
import com.ebanma.cloud.user.common.BaseContextHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class AuthInterceptor extends HandlerInterceptorAdapter {


    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String MESSAGE = "登录信息失效、请重新登录";
    private static final String BODY_MESSAGE = "请求参数不合法";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("AuthInterceptor start");
        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return true;
        }
        log.info("AuthInterceptor end");
        boolean result = check(request, response);
        return result;
    }


    private boolean check(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        log.info("================进入拦截器开始================");
        Result<ConcurrentHashMap<String, Object>> commonResult = new Result<>();

        final String requestHeader = request.getHeader("Auth");
        if (StringUtils.isNotBlank(requestHeader)) {
            String authToken = requestHeader;
            // 验证token是否过期,包含了验证jwt是否正确
            try {
                Claims claims = JwtUtil.parseJWT(authToken);
                BaseContextHandler.setPhone((String)claims.get("sub"));
                BaseContextHandler.setUserId((String)claims.get("sub"));
            } catch (Exception e) {
                // 有异常就是token解析失败
                return true;
            }

        }
        return true;
    }

}
