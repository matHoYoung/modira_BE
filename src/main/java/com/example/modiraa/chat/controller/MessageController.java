package com.example.modiraa.chat.controller;

import com.example.modiraa.chat.model.ChatMessage;
import com.example.modiraa.chat.model.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat/message") // /app/chat/message
    @SendTo("/topic") //
    public ChatMessage enter(ChatMessage message){
        if (MessageType.ENTER.equals(message.getType())){
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }
        log.info("sendMessage: {}", message);
        sendingOperations.convertAndSend("/topic" + message.getRoomId(),message);

        log.info("chatRoomId:{}" + message.getRoomId()); // 쳇룸 아이디가 서브크라이브에 박히는지 테스트 필요

        return message;
    }
}
