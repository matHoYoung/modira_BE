package com.example.modiraa.post.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class PostImage {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

// 사진을 많이올리는거면 ManyToOne 고 , 하나만올리는거면 oneTOone이니 fetch를 빼야함 .\

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;
    @Column(nullable = false)
    private String menu;

    @Column(nullable = false)
    private String imageurl;

}
