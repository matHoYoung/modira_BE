package com.example.modiraa.repository;

import com.example.modiraa.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    ChatRoom findByPostId(Long postId);
}
