package com.example.modiraa.post.controller;

import com.example.modiraa.loginAndRegister.auth.UserDetailsImpl;
import com.example.modiraa.post.dto.PostDetailResponseDto;
import com.example.modiraa.post.dto.PostListDto;
import com.example.modiraa.post.dto.PostRequestDto;
import com.example.modiraa.post.dto.PostsResponseDto;
import com.example.modiraa.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 모임 생성
    @PostMapping("/api/post")
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto postRequestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        postService.createPost(username, postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("모임 생성 완료");
    }

    // 모임 검색
    @GetMapping("/api/search/post")
    public ResponseEntity<Slice<PostsResponseDto>> searchPosts(@RequestParam(value = "title", defaultValue = "") String title,
                                                               @RequestParam(value = "address", defaultValue = "") String address,
                                                               @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 8) Pageable pageable,
                                                               @RequestParam(value = "lastId", defaultValue = "" + Long.MAX_VALUE) Long lastId){
        Page<PostsResponseDto> posts = postService.searchPosts(title, address, pageable, lastId);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    // 카테코리별 모임 더보기
    @GetMapping("/api/post")
    public ResponseEntity<Slice<PostsResponseDto>> getPosts(@RequestParam(value = "category", defaultValue = "") String category,
                                                            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 8) Pageable pageable){
        Page<PostsResponseDto> posts = postService.showPosts(category, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    // 메인 페이지 카테코리별 모임
    @GetMapping("/api/post/list")
    public ResponseEntity<PostListDto> getPostList(){
        PostListDto postList = postService.showPostList();
        return ResponseEntity.status(HttpStatus.OK).body(postList);
    }

    // 모임 상세페이지
    @GetMapping("/api/post/detail/{postId}")
    public ResponseEntity<PostDetailResponseDto> postDetail(@PathVariable Long postId){
        PostDetailResponseDto postDetail = postService.getPostDetail(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postDetail);
    }

    // 모임 삭제
    @DeleteMapping("/api/post/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

//    // 모임 조회
//    @GetMapping("/api/main/rooms")
//    public ResponseEntity<PostRequestDto> readPost(@AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {
//        PostRequestDto postRequestDto = postService.readPost(userDetails);
//        return ResponseEntity.status(HttpStatus.OK).body(postRequestDto);
//    }

}
