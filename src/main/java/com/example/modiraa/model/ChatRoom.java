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


//    @Column(nullable = false)
//    private int numberOfPeople;

    @OneToOne(mappedBy = "chatRoom")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNERMEMBER_ID")
    private Member ownerMember;

    public ChatRoom(Member ownerMember, Post post) {
        this.ownerMember = ownerMember;
        this.post = post;
        this.roomId = UUID.randomUUID().toString();

    }

    public ChatRoom(String uuid) {
        this.roomId = uuid;
    }
}