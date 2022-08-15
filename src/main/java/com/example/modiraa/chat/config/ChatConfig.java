package com.example.modiraa.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //메시지브로커가 지원하는 '웹소켓 메시지 처리'를 활성화
public class ChatConfig implements WebSocketMessageBrokerConfigurer {

/**
     * configureMessageBroker()
     * 메모리 기반의 Simple Message Broker를 활성화한다. 메시지 브로커는 "/topic"으로 시작하는 주소의 Subscriber들에게 메시지를 전달하는 역할을 한다.
     * 이때, 클라이언트가 서버로 메시지 보낼 때 붙여야 하는 prefix를 지정한다. "/app"로 지정하였다.
 *
     * registerStompEndpoints()
     * 기존의 WebSocket 설정과 마찬가지로 HandShake와 통신을 담당할 EndPoint를 지정한다. 클라이언트에서 서버로 WebSocket 연결을 하고 싶을 때, "/ws/chat"으로 요청을 보내도록 하였다.
     */

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); //SUB 1:n (subscribe 에 넣는 URL)
        registry.setApplicationDestinationPrefixes("/app"); //PUB 1:1 (Send 에 넣는 URL /app/chat/message )
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
    }

}
