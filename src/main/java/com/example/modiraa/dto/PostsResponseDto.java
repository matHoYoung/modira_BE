package com.example.modiraa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostsResponseDto {
    private Long postId;
    private String category;
    private String title;
    private String address;
    private String date;
    private int numberOfPeople;
    private String menu;
    private String gender;
    private String menuForImage;
}
