package com.example.modiraa.service;

import com.example.modiraa.model.ChatRoom;
import com.example.modiraa.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    // Redis CacheKeys
    private static final String CHAT_ROOMS = "CHAT_ROOM"; // 채팅룸 저장
    public static final String USER_COUNT = "USER_COUNT"; // 채팅룸에 입장한 클라이언트수 저장
    public static final String ENTER_INFO = "ENTER_INFO"; // 채팅룸에 입장한 클라이언트의 sessionId와 채팅룸 id를 맵핑한 정보 저장

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, ChatRoom> hashOpsChatRoom;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOpsEnterInfo;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOps;

    private final ChatRoomRepository chatRoomRepository;


    //채팅방생성
    @Transactional
    public ChatRoom createChatRoom() {
        String uuid = UUID.randomUUID().toString();
        ChatRoom chatRoom = new ChatRoom(uuid);

        hashOpsChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);

        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    // 채팅방 리스트
    public List<ChatRoom> findAllRoom() {
        return hashOpsChatRoom.values(CHAT_ROOMS);
    }

    // 유저가 입장한 채팅방 ID와 memberId 맵핑 정보 저장
    public void setUserEnterInfo(Long memberId, String roomId) {
        hashOpsEnterInfo.put(ENTER_INFO, Long.toString(memberId), roomId);
    }

    // memberId로 입장해 있는 채팅방 ID 조회
    public String getUserEnterRoomId(String memberId) {
        return hashOpsEnterInfo.get(ENTER_INFO, memberId);
    }

    // 유저 정보와 맵핑된 채팅방 ID 삭제
    public void removeUserEnterInfo(String memberId) {
        hashOpsEnterInfo.delete(ENTER_INFO, memberId);
    }

    // 채팅방 유저수 조회
    public long getUserCount(String roomId) {
        return Long.valueOf(Optional.ofNullable(valueOps.get(USER_COUNT + "_" + roomId)).orElse("0"));
    }

    // 채팅방에 입장한 유저수 +1
    public long plusUserCount(String roomId) {
        return Optional.ofNullable(valueOps.increment(USER_COUNT + "_" + roomId)).orElse(0L);
    }

    // 채팅방에 입장한 유저수 -1
    public long minusUserCount(String roomId) {
        return Optional.ofNullable(valueOps.decrement(USER_COUNT + "_" + roomId)).filter(count -> count > 0).orElse(0L);
    }
}
