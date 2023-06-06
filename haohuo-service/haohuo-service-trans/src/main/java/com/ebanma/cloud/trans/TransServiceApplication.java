package com.ebanma.cloud.trans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan(basePackages = {"com.ebanma.cloud.trans.dao", "com.ebanma.cloud.common.core"})
public class TransServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransServiceApplication.class, args);
    }

}
