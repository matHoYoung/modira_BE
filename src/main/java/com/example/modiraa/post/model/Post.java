package com.example.modiraa.post.model;

import com.example.modiraa.loginAndRegister.model.Member;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@Builder
@Entity // DB 테이블 역할을 합니다.
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

}
