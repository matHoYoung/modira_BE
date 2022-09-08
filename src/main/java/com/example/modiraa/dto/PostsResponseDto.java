package com.example.modiraa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostsResponseDto {
    private Long postId;
    private String category;
    private String title;
    private String date;
    private String time;
    private int numberOfPeople;
    private String menu;
    private String gender;
    private String age;
    private String menuForImage;
}
