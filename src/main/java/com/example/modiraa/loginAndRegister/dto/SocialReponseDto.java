package com.example.modiraa.loginAndRegister.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class SocialReponseDto {
    private Long id;
    private String username;
    private String nickname;
    private String profileImage;
    private String age;
    private String address;
    private String gender;
}
