package com.example.modiraa.LikeAndHate.controller;

import com.example.modiraa.LikeAndHate.service.LikesService;
import com.example.modiraa.loginAndRegister.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikesAndHateController {
    private LikesService likesService;

    @GetMapping("/api/user/score")
    public ResponseEntity<?> readScore(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getMember().getId();
        return likesService.userLikes(userId);
    }

}