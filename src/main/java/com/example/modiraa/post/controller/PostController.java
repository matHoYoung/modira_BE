package com.example.modiraa.post.controller;

import com.example.modiraa.post.dto.PostRequestDto;
import com.example.modiraa.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 모임 생성
    @PostMapping("/api/post")
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto postRequestDto) {
        postService.createPost(postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("모임 생성 완료");
    }
}
