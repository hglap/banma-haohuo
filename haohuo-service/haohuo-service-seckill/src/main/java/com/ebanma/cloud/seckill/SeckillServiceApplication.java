package com.ebanma.cloud.seckill;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDiscoveryClient

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.ebanma.cloud.seckill.dao"})
@EnableFeignClients({"com.ebanma.cloud.*.api.*"})
public class SeckillServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillServiceApplication.class, args);
    }

}

