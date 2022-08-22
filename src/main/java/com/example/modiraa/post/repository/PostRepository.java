package com.example.modiraa.post.repository;

import com.example.modiraa.loginAndRegister.model.Member;
import com.example.modiraa.post.dto.PostsResponseDto;
import com.example.modiraa.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByIdLessThanAndTitleContainingAndAddressContains(Long lastId, String title, String address, Pageable pageable);
    Page<Post> findAllByCategoryContains(String category, Pageable pageable);



    Page<Post> findAllByAddressContaining(String address, Pageable pageable);
    Page<Post> findAllByAddressContainingAndCategory(String address, String category, Pageable pageable);

//    Optional<Post> findByMemberOrderByIdDesc(Member member,Pageable pageable);
// 내가 작성한 모임 조회
    @Query("SELECT new com.example.modiraa.post.dto.PostsResponseDto(p.id, p.category, p.title, p.address, p.date, p.numberofpeople, p.menu, p.gender, p.menu)" +
            "from Post p " +
            "where p .member =:member " +
            "order by p.id desc")
    List<PostsResponseDto> MyPostRead(@Param("member")Member member, Pageable pageable);


    //내가 참석한 모임 조회
    @Query("SELECT new com.example.modiraa.post.dto.PostsResponseDto(p.id, p.category, p.title, p.address, p.date, p.numberofpeople, p.menu, p.gender, p.menu)" +
            "from Post p " +
            "where p .member =:member " +
            "order by p.id desc")
    List<PostsResponseDto> MyJoinRead(@Param("member")Member member, Pageable pageable);



}
