package com.example.modiraa.controller;

import com.example.modiraa.config.jwt.JwtAuthorizationFilter;
import com.example.modiraa.dto.ChatMessageRequestDto;
import com.example.modiraa.model.ChatMessage;
import com.example.modiraa.model.Member;
import com.example.modiraa.service.ChatMessageService;
import com.example.modiraa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final ChatMessageService chatMessageService;
    private final UserService userService;

    // websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
    @MessageMapping("/chat/message")
    public void message(@RequestBody ChatMessageRequestDto messageRequestDto, @Header("Authorization") String token) {
        Member member = jwtAuthorizationFilter.getMemberFromJwt(token);
        ChatMessage chatMessage = new ChatMessage(messageRequestDto, userService);
        chatMessage.setSender(member);
        chatMessageService.sendChatMessage(chatMessage);
    }
}
