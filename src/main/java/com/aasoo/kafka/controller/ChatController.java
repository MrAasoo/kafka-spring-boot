package com.aasoo.kafka.controller;

import com.aasoo.kafka.constant.KafkaConstants;
import com.aasoo.kafka.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle chat messages and send them to Kafka.
 * This controller listens for messages sent to the /app/chat endpoint
 * and forwards them to a Kafka topic.
 */
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @GetMapping("/info")
    public String home() {
        return "Hello, Kafka!";
    }

    @MessageMapping("/chat") // Client sends to /app/chat
    public void sendMessage(@Payload Message message) {
        message.setTimestamp(System.currentTimeMillis());
        kafkaTemplate.send(KafkaConstants.CHAT_TOPIC, message);
    }

    @PostMapping("/app/chat") // REST api request to /app/chat
    public String sendRestMessage(@RequestBody Message message) {
        message.setTimestamp(System.currentTimeMillis());
        kafkaTemplate.send(KafkaConstants.CHAT_TOPIC, message);
        return "Success";
    }
}
