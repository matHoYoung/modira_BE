package com.example.modiraa.LikeAndHate.controller;

import com.example.modiraa.LikeAndHate.service.LikesService;
import com.example.modiraa.loginAndRegister.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikesController {

    private final LikesService likesService;

    // 좋아요 기능
    @PostMapping("/api/likes/{userId}")
    public ResponseEntity<?> userLikes(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getMember().getId();
        return likesService.userLikes(userId);
    }

    // 좋아요 취소 기능
    @DeleteMapping("/api/likes/{userId}")
    public ResponseEntity<?> deletetLikes(@AuthenticationPrincipal UserDetailsImpl userDetails)  {
        Long userId = userDetails.getMember().getId();
        return likesService.deleteLikes(userId);
    }

}