package com.example.modiraa.LikeAndHate.controller;

import com.example.modiraa.LikeAndHate.service.HatesService;
import com.example.modiraa.loginAndRegister.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HatesController {
    private final HatesService hatesService;

    // 싫어요 기능
    @PostMapping("/api/hates/{userId}")
    public ResponseEntity<?> userHates(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getMember().getId();
        return hatesService.userHates(userId);
    }

    // 싫어요 취소 기능
    @DeleteMapping("/api/hates/{userId}")
    public ResponseEntity<?> deletetHates(@AuthenticationPrincipal UserDetailsImpl userDetails)  {
        Long userId = userDetails.getMember().getId();
        return hatesService.deleteHates(userId);
    }

}
