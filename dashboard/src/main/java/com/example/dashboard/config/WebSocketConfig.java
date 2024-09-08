package com.example.dashboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        // Register the "/ws" endpoint, enabling the SockJS protocol.
//        // SockJS is used to enable fallback options for browsers that don’t support WebSocket.
//        registry.addEndpoint("/ws").withSockJS();
//    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:3000").withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Use the "/topic" prefix for outgoing WebSocket communication
        registry.enableSimpleBroker("/topic");
        // Use the "/app" prefix for methods annotated with @MessageMapping
        registry.setApplicationDestinationPrefixes("/app");
    }
}
