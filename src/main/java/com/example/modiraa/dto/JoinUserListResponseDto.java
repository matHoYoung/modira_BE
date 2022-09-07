package com.example.modiraa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinUserListResponseDto {

    private Long userId;
    private String nickname;
    private String userProfile;

}
