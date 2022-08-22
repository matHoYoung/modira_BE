package com.example.modiraa.post.service;

import com.example.modiraa.LikeAndHate.repository.HatesRepository;
import com.example.modiraa.LikeAndHate.repository.LikesRepository;
import com.example.modiraa.loginAndRegister.auth.UserDetailsImpl;
import com.example.modiraa.loginAndRegister.model.Member;
import com.example.modiraa.post.dto.PostDetailResponseDto;
import com.example.modiraa.post.dto.PostListDto;
import com.example.modiraa.post.dto.PostsResponseDto;
import com.example.modiraa.post.model.Post;
import com.example.modiraa.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostReadService {
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final HatesRepository hatesRepository;

    // 모임 검색
    public Page<PostsResponseDto> searchPosts(String keyword, String address, Pageable pageable, Long lastId) {
        log.info("keyword -> {}", keyword);
        log.info("address -> {}", address);
        log.info("pageable -> {}", pageable);
        log.info("lastId -> {}", lastId);

        Page<Post> posts = postRepository
                .findAllByIdLessThanAndTitleContainingAndAddressContainsOrMenuContaining(lastId, keyword, address, pageable, keyword);

        log.info("result=> {}", posts);
        log.info("result=> {}", posts.getContent());

        return postResponseDto(posts);

    }

    // 카테고리별 모임
    public Page<PostsResponseDto> showPosts(String category, Pageable pageable, Long lastId) {
        log.info("category -> {}", category);
        log.info("lastId -> {}", lastId);

        Page<Post> posts = postRepository.findAllByIdLessThanAndCategoryContains(lastId, category, pageable);

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

    // 메인 페이지 카테코리별 모임
    public PostListDto showPostListMember(UserDetailsImpl userDetails) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 8, sort);

        String memberAddress = userDetails.getMember().getAddress();

        Page<Post> postAll = postRepository.findAllByAddressContaining(memberAddress, pageable);
        Page<Post> postGoldenBell = postRepository.findAllByAddressContainingAndCategory(memberAddress, "골든벨", pageable);
        Page<Post> postDutchPay = postRepository.findAllByAddressContainingAndCategory(memberAddress,"N빵", pageable);

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
                        .menuForImage(p.getPostImage().getImageurl())
                        .build()
        );
    }

    // 모임 상세페이지
    public PostDetailResponseDto getPostDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("게시글이 없습니다."));

        Long score = likesRepository.likesCount(post.getMember()) - hatesRepository.hatesCount(post.getMember());

        return PostDetailResponseDto.builder()
                .category(post.getCategory())
                .title(post.getTitle())
                .contents(post.getContents())
                .address(post.getAddress())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .date(post.getDate())
                .numberOfPeople(post.getNumberofpeople())
                .menu(post.getMenu())
                .limitGender(post.getGender())
                .limitAge(post.getAge())
                .writerProfileImage(post.getMember().getProfileImage())
                .writerNickname(post.getMember().getNickname())
                .writerGender(post.getMember().getGender())
                .writerAge(post.getMember().getAge())
                .writerScore(score)
                .build();
    }

    //내가 쓴 참석 모임 조회
    public List<PostsResponseDto> getMyReadPost(UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        Pageable pageable = PageRequest.ofSize(1);
        return postRepository.MyPostRead(member, pageable);
    }

    //내가 참석한 모임 조회
    public List<PostsResponseDto> getMyJoinPost(UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        Pageable pageable = PageRequest.ofSize(1);
        return postRepository.MyJoinRead(member, pageable);
    }
}

