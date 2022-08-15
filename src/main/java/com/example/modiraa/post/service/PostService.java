package com.example.modiraa.post.service;

import com.example.modiraa.post.dto.PostRequestDto;
import com.example.modiraa.post.dto.PostsResponseDto;
import com.example.modiraa.post.model.Post;
import com.example.modiraa.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 모임 생성
    public void createPost(PostRequestDto postRequestDto) {
        Post post = Post.builder()
                .category(postRequestDto.getCategory())
                .title(postRequestDto.getTitle())
                .contents(postRequestDto.getContents())
                .address(postRequestDto.getAddress())
                .date(postRequestDto.getDate())
                .numberofpeople(postRequestDto.getNumberOfPeople())
                .menu(postRequestDto.getMenu())
                .gender(postRequestDto.getGender())
                .age(postRequestDto.getAge())
                .build();
        postRepository.save(post);
    }

    // 모임 검색
    public Page<PostsResponseDto> searchPosts(String title, String address, Pageable pageable, Long lastId) {
        log.info("title -> {}", title);
        log.info("address -> {}", address);
        log.info("pageable -> {}", pageable);
        log.info("lastId -> {}", lastId);

        Page<Post> posts = postRepository
                .findAllByIdLessThanAndTitleContainingAndAddressContains(lastId, title, address, pageable);

        log.info("result=> {}", posts);
        log.info("result=> {}", posts.getContent());

        return postResponseDto(posts);

    }

    private Page<PostsResponseDto> postResponseDto(Page<Post> postSlice) {
        return postSlice.map(p ->
                PostsResponseDto.builder()
                        .postId(p.getId())
                        .title(p.getTitle())
                        .category(p.getCategory())
                        .address(p.getAddress())
                        .date(p.getDate())
                        .numberOfPeople(p.getNumberofpeople())
                        .menu(p.getMenu())
                        .build()
        );
    }

    // 모임 삭제
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        postRepository.delete(post);
    }
}
