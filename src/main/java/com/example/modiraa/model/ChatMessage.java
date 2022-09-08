package com.example.modiraa.model;

import com.example.modiraa.dto.ChatMessageRequestDto;
import com.example.modiraa.service.UserService;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    // 메시지 타입 : 입장, 채팅, 퇴장
    public enum MessageType {
        ENTER, TALK, QUIT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private MessageType type;

    @Column
    private String roomId; // 방번호

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member sender; // 메시지 보낸사람

    @Column(length = 100000)
    private String message; // 메시지

    @Column
    private long userCount; // 채팅방 인원수, 채팅방 내에서 메시지가 전달될때 인원수 갱신시 사용

    @Builder
    public ChatMessage(MessageType type, String roomId, Member sender, String message, long userCount) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.userCount = userCount;
    }

    @Builder
    public ChatMessage(ChatMessageRequestDto chatMessageRequestDto, UserService userService) {
        this.type = chatMessageRequestDto.getType();
        this.roomId = chatMessageRequestDto.getRoomId();
        this.sender =  chatMessageRequestDto.getSender();
        this.message = chatMessageRequestDto.getMessage();
        this.userCount = chatMessageRequestDto.getUserCount();
    }
}