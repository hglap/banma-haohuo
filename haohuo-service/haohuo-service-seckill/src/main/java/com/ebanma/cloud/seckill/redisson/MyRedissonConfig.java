package com.ebanma.cloud.gateway.app.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

/**
 * @author banma-
 * @version $ Id: MyRedissonConfig, v 0.1 2023/06/07 17:10 banma- Exp $
 */

@Configuration
public class MyRedissonConfig {

    @Value("&{spring.redis.host}")
    String redisHost;
//    String redisHost = "127.0.0.1";

    @Value("&{spring.redis.port}")
    String redisPort;
//    String redisPort = "6379";

    @Value("&{spring.redis.password}")
    String redisPassword;
//    String redisPassword = "password";


    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress("redis://"+redisHost + ":" + redisPort);
        System.out.println("redisHost:"+redisHost+"redisPort:"+redisPort+"redisPassword:"+redisPassword);
        if(StringUtils.hasLength(redisPassword)){
            serverConfig.setAddress(redisPassword);
        }
        return Redisson.create(config);
    }

}