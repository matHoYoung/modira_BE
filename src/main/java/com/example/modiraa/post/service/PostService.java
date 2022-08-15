package com.example.modiraa.post.service;

import com.example.modiraa.post.dto.PostRequestDto;
import com.example.modiraa.post.model.Post;
import com.example.modiraa.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
