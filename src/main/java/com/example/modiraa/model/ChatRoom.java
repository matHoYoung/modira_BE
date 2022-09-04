package com.example.modiraa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "chatroom_id")
    private Long id;

    @Column
    private String roomId;

    @Column
    private long userCount; //채팅방 인원 수

    @OneToOne(mappedBy = "chatRoom")
    private Post post;

    public static ChatRoom create() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        return chatRoom;
    }

    public ChatRoom(String uuid) {
        this.roomId = uuid;
    }
}