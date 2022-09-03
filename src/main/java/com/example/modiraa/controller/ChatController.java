package com.example.modiraa.controller;

import com.example.modiraa.config.jwt.JwtAuthorizationFilter;
import com.example.modiraa.model.ChatMessage;
import com.example.modiraa.repository.ChattingRoomRepository;
import com.example.modiraa.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final ChattingRoomRepository chattingRoomRepository;
    private final ChatService chatService;

    // websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
    @MessageMapping("/chat/message")
    public void message(ChatMessage message, @Header("Authorization") String token) {
        String nickname = jwtAuthorizationFilter.getUserNameFromJwt(token);
        log.info("nickname: {}", nickname);

        // 로그인 회원 정보로 대화명 설정
        message.setSender(nickname);
        // 채팅방 인원수 세팅
        message.setUserCount(chattingRoomRepository.getUserCount(message.getRoomId()));

        // Websocket에 발행된 메시지를 redis로 발행(publish)
        chatService.sendChatMessage(message);
    }
}
