package com.example.modiraa.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostDetailResponseDto {
    private String category;
    private String title;
    private String contents;
    private String address;
    private double latitude;
    private double longitude;
    private String date;
    private int numberOfPeople;
    private String menu;
    private String limitGender;
    private String limitAge;
    private String writerProfileImage;
    private String writerNickname;
    private String writerGender;
    private String writerAge;
}
