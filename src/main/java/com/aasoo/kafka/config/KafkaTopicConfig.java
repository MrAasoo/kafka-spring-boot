package com.aasoo.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.aasoo.kafka.constant.KafkaConstants;

/**
 * Configuration class for Kafka topics.
 * This class defines the Kafka topics used in the application.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Bean definition for the chat topic.
     * This topic is used to send and receive chat messages.
     *
     * @return a NewTopic instance representing the chat topic
     */
    @Bean
    public NewTopic chatTopic() {
        return TopicBuilder.name(KafkaConstants.CHAT_TOPIC)
                .partitions(3)    // Higher partitions = better parallelism
                .replicas(1)      // Number of copies of data (use 3 for production)
                .build();
    }
}
