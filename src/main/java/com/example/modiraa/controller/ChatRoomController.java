package com.example.modiraa.controller;

import com.example.modiraa.dto.ChatMessageResponseDto;
import com.example.modiraa.model.ChatRoom;
import com.example.modiraa.service.ChatMessageService;
import com.example.modiraa.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> roomList() {
        List<ChatRoom> chatRooms = chatRoomService.findAllRoom();
        chatRooms.stream().forEach(room -> room.setUserCount(chatRoomService.getUserCount(room.getRoomId())));
        return chatRooms;
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(@PathVariable String roomId) {
        return roomId;
    }

    // 해당 채팅방의 메세지 조회
    @GetMapping("/messages/{roomId}")
    public Page<ChatMessageResponseDto> getRoomMessage(@PathVariable String roomId, @PageableDefault Pageable pageable) {
        return chatMessageService.getChatMessageByRoomId(roomId, pageable);
    }

}