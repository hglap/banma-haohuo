package com.ebanma.cloud.mall.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * @author: why
 * @date: 2023/6/12
 * @time: 11:10
 * @description:
 */
@Configuration
public class ElasticsearchConfig {
    @Bean
    public RestHighLevelClient elasticsearchClient(){
        ClientConfiguration configuration= ClientConfiguration.builder()
                .connectedTo("30.16.95.143:9200")
                .build();
        RestHighLevelClient client=RestClients.create(configuration).rest();
        return client;
    }
}
