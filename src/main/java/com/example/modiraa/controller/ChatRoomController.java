package com.example.modiraa.controller;

import com.example.modiraa.model.ChatRoom;
import com.example.modiraa.repository.ChattingRoomRepository;
import com.example.modiraa.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChattingRoomRepository chattingRoomRepository;
    private final ChatRoomService chatRoomService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return model.toString();
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/room/list")
    @ResponseBody
    public List<ChatRoom> roomList() {
        return chatRoomService.findAllRoom();
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chattingRoomRepository.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        log.info("creatRoomName:{}" + name);
        return chattingRoomRepository.createChatRoom();
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return roomId;
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chattingRoomRepository.findRoomById(roomId);
    }
}