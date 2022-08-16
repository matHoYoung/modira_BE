package com.example.modiraa.LikeAndHate.repository;

import com.example.modiraa.LikeAndHate.model.Hates;
import com.example.modiraa.loginAndRegister.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HatesRepository extends JpaRepository<Hates, Long> {

    List<Hates> findByUserId(Long userId);

    Optional<Hates> findByMember(Member member);
}
