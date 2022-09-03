package com.example.modiraa.repository;

import com.example.modiraa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByUsername(String username);
    Optional<String> findByNickname(String nickname);

    <T> Optional<T> findByNickname(String nickname, Class <T>type);
}