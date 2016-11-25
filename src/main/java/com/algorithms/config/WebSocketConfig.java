package com.algorithms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Specifies configuration for any interconnection done
 * through web sockets
 */
@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * Defines prefix for messages that are bound for @{code @MessageMapping}
     * annotated methods
     *
     * @param config object used to configure message broker
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/visualize");
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * Enables SockJS fallback options so that alternate transports may be used
     * if WebSocket is not available.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/visual-alg").withSockJS();
    }
}