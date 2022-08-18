package com.example.modiraa.post.repository;

import com.example.modiraa.loginAndRegister.model.Member;
import com.example.modiraa.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByIdLessThanAndTitleContainingAndAddressContains(Long lastId,
                                                                       String title,
                                                                       String address,
                                                                       Pageable pageable);
    Page<Post> findAllByCategoryContains(String category, Pageable pageable);

//    Optional<Post> findByMemberOrderByIdDesc(Member member,Pageable pageable);
}
