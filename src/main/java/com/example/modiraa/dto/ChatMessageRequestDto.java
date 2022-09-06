package com.example.modiraa.dto;

import com.example.modiraa.model.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatMessageRequestDto {
    private ChatMessage.MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private long userCount;
}
