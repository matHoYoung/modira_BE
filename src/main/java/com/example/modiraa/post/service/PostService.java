package com.example.modiraa.post.service;

import com.example.modiraa.loginAndRegister.auth.UserDetailsImpl;
import com.example.modiraa.loginAndRegister.model.Member;
import com.example.modiraa.loginAndRegister.repository.UserRepository;
import com.example.modiraa.post.dto.PostListDto;
import com.example.modiraa.post.dto.PostRequestDto;
import com.example.modiraa.post.dto.PostsResponseDto;
import com.example.modiraa.post.model.Post;
import com.example.modiraa.post.repository.PostImageRepository;
import com.example.modiraa.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                .member(member)
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

    // 카테고리별 모임
    public Page<PostsResponseDto> showPosts(String category, Pageable pageable) {
        log.info("category -> {}", category);

        Page<Post> posts = postRepository.findAllByCategoryContains(category, pageable);

        log.info("result=> {}", posts);
        log.info("result=> {}", posts.getContent());

        return postResponseDto(posts);
    }

    // 메인 페이지 카테코리별 모임
    public PostListDto showPostList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 8, sort);

        Page<Post> postAll = postRepository.findAll(pageable);
        Page<Post> postGoldenBell = postRepository.findAllByCategoryContains("골든벨", pageable);
        Page<Post> postDutchPay = postRepository.findAllByCategoryContains("N빵", pageable);


        PostListDto postListDto = new PostListDto();

        postListDto.setPostAll(postResponseDto(postAll));
        postListDto.setPostGoldenBell(postResponseDto(postGoldenBell));
        postListDto.setPostDutchPay(postResponseDto(postDutchPay));

        return postListDto;
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
                        .menuForImage(postImageRepository.findByMenu(p.getMenu()).getImageurl())
                        .build()
        );
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
