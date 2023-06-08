package com.ebanma.cloud.trans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan(basePackages = {"com.ebanma.cloud.trans.dao"})
public class TransServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransServiceApplication.class, args);
    }

}
