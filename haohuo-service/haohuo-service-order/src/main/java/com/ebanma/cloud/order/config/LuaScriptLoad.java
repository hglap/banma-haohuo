package com.ebanma.cloud.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @author tcm
 * @version 1.0.0
 * @description 加载Lua脚本
 * @date 2023/3/24 15:47
 **/
@Configuration
public class LuaScriptLoad {

    /**
     * 注意泛型用Long
     * 否则会报 io.lettuce.core.output.ValueOutput does not support set(long)
     * @return
     */
    @Bean
    public DefaultRedisScript<Long> getLuaScript() {
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/sku.lua")));
        defaultRedisScript.setResultType(Long.class);
        return defaultRedisScript;
    }

}
