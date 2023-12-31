package com.ebanma.cloud.gateway.app.filter;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.common.enums.ResultCode;
import com.ebanma.cloud.common.pojo.AppUserToken;
import com.ebanma.cloud.common.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidMallUserTokenGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        final List<String> ignoreURLs = new ArrayList<>();
        ignoreURLs.add("/user-service/user/login");
        ignoreURLs.add("/user-service/user/getSmsCode");
        ignoreURLs.add("/user-service/user/checkCode");
        ignoreURLs.add("/user/login");
        ignoreURLs.add("/user/getSmsCode");
        ignoreURLs.add("/user/checkCode");

        // 登录注册接口，直接放行
        if (ignoreURLs.contains(exchange.getRequest().getURI().getPath())) {
            return chain.filter(exchange);
        }

        HttpHeaders headers = exchange.getRequest().getHeaders();

        if (headers == null || headers.isEmpty()) {
            // 返回错误提示
            return wrapErrorResponse(exchange, chain);
        }

        String token = headers.getFirst("Auth");

        if (!StringUtils.hasText(token)) {
            // 返回错误提示
            return wrapErrorResponse(exchange, chain);
        }
        try{
            Claims claims = JwtUtil.parseJWT(token);
        }catch(Exception e){
            return wrapErrorResponse(exchange, chain);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    Mono<Void> wrapErrorResponse(ServerWebExchange exchange, GatewayFilterChain chain) {
        Result result = ResultGenerator.genErrorResult(ResultCode.UNAUTHORIZED.code(), "无权限访问");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode resultNode = mapper.valueToTree(result);
        byte[] bytes = resultNode.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap(bytes);
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        return exchange.getResponse().writeWith(Flux.just(dataBuffer));
    }

}