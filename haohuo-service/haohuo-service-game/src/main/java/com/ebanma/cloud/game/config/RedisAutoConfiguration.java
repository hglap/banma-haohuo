package com.ebanma.cloud.game.config;

/**
 * @author banma-
 * @version $ Id: RedisAutoConfiguration, v 0.1 2023/06/14 15:22 banma- Exp $
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration(proxyBeanMethods = false)
public class RedisAutoConfiguration {
    @Bean
    public DefaultRedisScript<Long> redisScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        //resource目录下的scripts文件下的.lua文件
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/draw.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }
}