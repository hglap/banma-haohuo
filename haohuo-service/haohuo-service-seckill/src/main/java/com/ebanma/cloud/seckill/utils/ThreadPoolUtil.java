package com.ebanma.cloud.seckill.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 崔国垲
 * @version $ Id: ThreadPoolUtil, v 0.1 2023/06/12 15:55 banma-0197 Exp $
 */
@Configuration
public class ThreadPoolUtil {

    public static final int CORE_POOL_SIZE = 5;
    public static final int CORE_POOL_SIZE_1 = 3;

    @Bean
    public ExecutorService threadPoolUtil1() {
        return Executors.newFixedThreadPool(CORE_POOL_SIZE);
    }

    @Bean
    public  ExecutorService threadPoolUtil2() {
        return Executors.newFixedThreadPool(CORE_POOL_SIZE_1);
    }

}
