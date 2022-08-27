package com.example.modiraa.repository;

import com.example.modiraa.model.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    PostImage findByMenu(String menu);
}
