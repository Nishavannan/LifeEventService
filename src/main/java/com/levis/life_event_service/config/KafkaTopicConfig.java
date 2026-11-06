package com.levis.life_event_service.config;

//Used to create kafka topic

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic lifeEventTopic(){
        return TopicBuilder.name("levis.customer.loyality.life-events")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic smsTopic(){
        return TopicBuilder.name("customer.comms.sms")
                .partitions(2)
                .replicas(1)
                .build();
    }
}
