package com.example.modiraa.repository;

import com.example.modiraa.dto.myPostsResponseDto;
import com.example.modiraa.model.Member;
import com.example.modiraa.model.Post;
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

    Post findByChatRoomId(Long chatRoomId);


    @Query(value = "SELECT * FROM Post WHERE id < :lastId AND address LIKE :address% AND (menu LIKE %:keyword% OR title LIKE %:keyword% OR contents LIKE %:keyword% )",
            nativeQuery = true)
    Page<Post> selectPost(@Param("lastId") Long lastId,
                          @Param("address") String address,
                          @Param("keyword") String keyword,
                          Pageable pageable);
                          

    // Optional<Post> findByMemberOrderByIdDesc(Member member,Pageable pageable);
    // 내가 작성한 모임 조회
   @Query("SELECT new com.example.modiraa.dto.myPostsResponseDto(p.id, p.title, PI.imageurl, p.menu)" +
            "from Post p left outer join PostImage PI on PI.menu=p.menu " +
            "where p .member =:member " +
            "order by p.id desc")
    List<myPostsResponseDto> MyPostRead(@Param("member")Member member, Pageable pageable);


   // 룸아이디 꺼내기
    Post findByTitle(String title);

}
