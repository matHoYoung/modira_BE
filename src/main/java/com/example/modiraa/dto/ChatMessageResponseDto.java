package com.example.modiraa.dto;

import com.example.modiraa.model.ChatMessage;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageResponseDto {
    private ChatMessage.MessageType type;
    private String roomId;
    private Long senderId;
    private String sender;
    private String profileImage;
    private String message;
    private long userCount;
}
