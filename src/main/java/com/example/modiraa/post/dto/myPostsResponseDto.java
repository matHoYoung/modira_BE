package com.example.modiraa.post.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class myPostsResponseDto {

    private Long postId;
    private String title;
    private String menuForImage;
    private String date;
    private String category;
    private int numberOfPeople;
    private String menu;
    private String contents;

}
