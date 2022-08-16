package com.example.modiraa.post.repository;

import com.example.modiraa.post.model.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    PostImage findByMenu(String menu);
}
