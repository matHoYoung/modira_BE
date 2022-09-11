package com.example.modiraa.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Post {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //카테고리 이름
    @Column(nullable = false)
    private String category;

    //제목
    @Column(nullable = false)
    private String title;

    //내용
    @Column(nullable = false)
    private String contents;

    //주소
    @Column(nullable = false)
    private String address;

    //위도
    @Column(nullable = false)
    private double latitude;

    //경도
    @Column(nullable = false)
    private double longitude;

    //날짜
    @Column(nullable = false)
    private String date;

    //시간
    @Column(nullable = false)
    private String time;

    //인원 수
    @Column(nullable = false)
    private int numberofpeople;

    //음식 메뉴
    @Column(nullable = false)
    private String menu;

    //성별
    @Column(nullable = false)
    private String gender;

    //나이대
    @Column(nullable = false)
    private String age;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "postimage_id")
    private PostImage postImage;

    @OneToOne
    @JoinColumn(name="chatroom_id")
    private ChatRoom chatRoom;

    public void updateRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

}
