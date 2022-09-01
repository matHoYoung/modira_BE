package com.example.modiraa.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ChatMessage {

    public enum MessageType {
        ENTER, TALK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 메시지 타입 : 입장, 채팅
    @Column
    @Enumerated(value = EnumType.STRING)
    private MessageType type;

    @Column
    private String roomId; // 방번호

    @Column
    private String sender; // 메시지 보낸사람

    @Column(length = 100000)
    private String message; // 메시지
}