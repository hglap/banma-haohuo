package com.ebanma.cloud.seckill.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 崔国垲
 * @version $ Id: KafkaConfig, v 0.1 2023/06/09 15:27 banma-0197 Exp $
 */
@Configuration
public class KafkaConfig {
    // 创建一个名为testtopic的Topic并设置分区数为8，分区副本数为2
    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("userlog", 8, (short) 2);
    }
    // 如果要修改分区数，只需修改配置值重启项目即可
    // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
    @Bean
    public NewTopic updateTopic() {
        return new NewTopic("userlog", 10, (short) 2);
    }
}
