package com.example.modiraa.repository;

import com.example.modiraa.model.ChatRoom;
import com.example.modiraa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    Optional<ChatRoom> findByRoomId(String roomId);

    ChatRoom findByPost(Post post);
}
