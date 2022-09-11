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
    private long userCount;

    @Column(nullable = false)
    private int currentPeople;

    @Column(nullable = false)
    private int maxPeople;

    @OneToOne(mappedBy = "chatRoom")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNERMEMBER_ID")
    private Member ownerMember;

    public ChatRoom(Member ownerMember, Post post, int maxPeople) {
        this.ownerMember = ownerMember;
        this.post = post;
        this.roomId = UUID.randomUUID().toString();
        this.currentPeople = 1;
        this.maxPeople = maxPeople;

    }

    public void updateCurrentPeople() {
        this.currentPeople = this.currentPeople + 1;
    }
    public void minusCurrentPeople() {
        this.currentPeople = this.currentPeople - 1;
    }

    public ChatRoom(String uuid) {
        this.roomId = uuid;
    }

}