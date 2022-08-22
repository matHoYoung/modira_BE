package com.example.modiraa.mypage.service;

import com.example.modiraa.LikeAndHate.repository.HatesRepository;
import com.example.modiraa.LikeAndHate.repository.LikesRepository;
import com.example.modiraa.loginAndRegister.auth.UserDetailsImpl;
import com.example.modiraa.loginAndRegister.model.Member;
import com.example.modiraa.loginAndRegister.repository.UserRepository;
import com.example.modiraa.mypage.dto.UserProfileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final HatesRepository hatesRepository;
// 유저 프로필 조회
    public UserProfileResponseDto getProfile(Long id) throws IllegalAccessException {

        Member member = userRepository.findById(id)
                .orElseThrow(()-> new IllegalAccessException("유저의 정보가 없습니다."));

        Long score = likesRepository.likesCount(member) - hatesRepository.hatesCount(member);

        return UserProfileResponseDto.builder()
                .address(member.getAddress())
                .age(member.getAge())
                .userProfile(member.getProfileImage())
                .gender(member.getGender())
                .kakaoNickname(member.getUsername())
                .score(score)
                .build();


    }
//마이프로필 조회
    public UserProfileResponseDto getMyProfileRead(UserDetailsImpl userDetails) {

        Member member = userDetails.getMember();

        Long score = likesRepository.likesCount(member) - hatesRepository.hatesCount(member);

        return UserProfileResponseDto.builder()
                .address(member.getAddress())
                .age(member.getAge())
                .userProfile(member.getProfileImage())
                .gender(member.getGender())
                .kakaoNickname(member.getUsername())
                .score(score)
                .build();
    }
}
