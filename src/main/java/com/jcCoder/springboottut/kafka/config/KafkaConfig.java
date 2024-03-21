package com.jcCoder.springboottut.kafka.config;

import com.jcCoder.springboottut.kafka.constant.KafkaConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic topic(){
        return TopicBuilder
                .name(KafkaConstant.DEPARTMENT_DETAILS)
                .build();
    }

}
