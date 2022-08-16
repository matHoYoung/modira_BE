package com.example.modiraa.LikeAndHate.repository;

import com.example.modiraa.LikeAndHate.model.Likes;
import com.example.modiraa.loginAndRegister.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    List<Likes> findByUserId(Long userId);

    Optional<Likes> findByMember(Member member);
}