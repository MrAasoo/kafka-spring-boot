package com.aasoo.kafka.service;

import com.aasoo.kafka.constant.KafkaConstants;
import com.aasoo.kafka.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Service to consume messages from Kafka and broadcast them to WebSocket clients.
 * This service listens to a Kafka topic and forwards received messages to
 * all subscribed WebSocket clients.
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    // Template to send messages to WebSocket clients
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Consume messages from the specified Kafka topic.
     * Upon receiving a message, it is broadcasted to all clients subscribed to /topic/public.
     *
     * @param message the message received from Kafka
     */
    @KafkaListener(
            topics = KafkaConstants.CHAT_TOPIC,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(Message message) {
        // Broadcast to all clients subscribed to /topic/public
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}
