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
    Page<Post> findAllByIdLessThanAndCategoryContains(Long lastId, String category, Pageable pageable);

    Page<Post> findAllByCategoryContains(String category, Pageable pageable);

    Page<Post> findAllByAddressContaining(String address, Pageable pageable);
    Page<Post> findAllByAddressContainingAndCategory(String address, String category, Pageable pageable);


    @Query(value = "SELECT * FROM Post WHERE id < :lastId AND address LIKE :address% AND (menu LIKE %:menu% OR title LIKE %:title% )",
            nativeQuery = true)
    Page<Post> selectPost(@Param("lastId") Long lastId,
                          @Param("address") String address,
                          @Param("menu") String menu,
                          @Param("title") String title,
                          Pageable pageable);

    // Optional<Post> findByMemberOrderByIdDesc(Member member,Pageable pageable);
    // 내가 작성한 모임 조회
    @Query("SELECT new com.example.modiraa.post.dto.PostsResponseDto(p.id, p.category, p.title, p.address, p.date, p.numberofpeople, p.menu, p.gender, p.postImage.imageurl)" +
            "from Post p " +
            "where p .member =:member " +
            "order by p.id desc")
    List<PostsResponseDto> MyPostRead(@Param("member") Member member, Pageable pageable);


    //내가 참석한 모임 조회
    @Query("SELECT new com.example.modiraa.post.dto.PostsResponseDto(p.id, p.category, p.title, p.address, p.date, p.numberofpeople, p.menu, p.gender, p.postImage.imageurl)" +
            "from Post p  " +
            "where p .member =:member " +
            "order by p.id desc")
    List<PostsResponseDto> MyJoinRead(@Param("member") Member member, Pageable pageable);

}
