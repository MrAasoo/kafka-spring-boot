package com.aasoo.kafka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket configuration class to set up STOMP endpoints and message broker.
 * This configuration enables real-time communication using WebSockets.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Register STOMP endpoints mapping each to a specific URL and (optionally)
     * enabling and configuring SockJS fallback options.
     *
     * @param registry the registry to configure the STOMP endpoints
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // The endpoint clients use to connect
        registry.addEndpoint("/ws-chat").withSockJS();
    }

    /**
     * Configure message broker options.
     *
     * @param registry the registry to configure the message broker
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Prefix for messages sent FROM client TO server
        registry.setApplicationDestinationPrefixes("/app");
        // Prefix for messages sent FROM server TO client
        registry.enableSimpleBroker("/topic");
    }

}
