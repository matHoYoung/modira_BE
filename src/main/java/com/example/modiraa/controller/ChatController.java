package com.example.modiraa.controller;

import com.example.modiraa.config.jwt.JwtAuthorizationFilter;
import com.example.modiraa.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final ChannelTopic channelTopic;


    @MessageMapping("/chat/message")
    public void message(ChatMessage message, @Header("Authorization") String token) {
        String nickname = jwtAuthorizationFilter.getUserNameFromJwt(token);
        log.info("nickname: {}", nickname);

        // 로그인 회원 정보로 대화명 설정
        message.setSender(nickname);

        // 채팅방 입장시에는 대화명과 메시지를 자동으로 세팅한다.
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setSender("[알림]");
            message.setMessage(nickname + "님이 입장하셨습니다.");
        }
        log.info("sendMessage: {}", message.getMessage());
        log.info("chatRoomId: {}" , message.getRoomId());

        // Websocket에 발행된 메시지를 redis로 발행(publish)
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}
