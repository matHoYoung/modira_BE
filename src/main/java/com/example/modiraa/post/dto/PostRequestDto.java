package com.example.modiraa.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PostRequestDto {
    private String category;
    private String title;
    private String contents;
    private String address;
    private String date;
    private int numberOfPeople;
    private String menu;
    private String gender;
    private String age;
}
