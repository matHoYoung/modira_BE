package com.example.modiraa.post.service;

import com.example.modiraa.loginAndRegister.auth.UserDetailsImpl;
import com.example.modiraa.loginAndRegister.model.Member;
import com.example.modiraa.loginAndRegister.repository.UserRepository;
import com.example.modiraa.post.dto.PostRequestDto;
import com.example.modiraa.post.model.Post;
import com.example.modiraa.post.model.PostImage;
import com.example.modiraa.post.repository.PostImageRepository;
import com.example.modiraa.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    // 모임 생성
    public void createPost(String username, PostRequestDto postRequestDto) {
        Member member = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("다시 로그인해 주세요."));

        PostImage postImage = postImageRepository.findByMenu(postRequestDto.getMenu());

        Post post = Post.builder()
                .category(postRequestDto.getCategory())
                .title(postRequestDto.getTitle())
                .contents(postRequestDto.getContents())
                .address(postRequestDto.getAddress())
                .latitude(postRequestDto.getLatitude())
                .longitude(postRequestDto.getLongitude())
                .date(postRequestDto.getDate())
                .numberofpeople(postRequestDto.getNumberOfPeople())
                .menu(postRequestDto.getMenu())
                .gender(postRequestDto.getGender())
                .age(postRequestDto.getAge())
                .member(member)
                .postImage(postImage)
                .build();

        postRepository.save(post);
    }

    // 모임 삭제
    public void deletePost(Long postId, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Long memberId = userDetails.getMember().getId();

        if (memberId.equals(post.getMember().getId())) {
            postRepository.delete(post);
        } else {
            throw new IllegalArgumentException("모임을 삭제할 권한이 없습니다");
        }
    }


}
