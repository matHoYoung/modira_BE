package com.example.modiraa.LikeAndHate.service;

import com.example.modiraa.LikeAndHate.model.Likes;
import com.example.modiraa.LikeAndHate.repository.LikesRepository;
import com.example.modiraa.loginAndRegister.model.Member;
import com.example.modiraa.loginAndRegister.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;

    //유저의 평가 점수 +1점 부여하고 싶을때
    public ResponseEntity<?> userLikes(Long userId) {
        //USERID 아이디로 USER 를 찾아서 저장
        Member member = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저가 없습니다"));
        Optional<Likes> Likefound = likesRepository.findByMember(member);
        //이미 있는 USER가 들어오면 오류 메시지 전달
        if(Likefound.isPresent()){
            return new ResponseEntity<>("이미 평가를 하셨습니다.", HttpStatus.valueOf(400));
        }
        //새로운 Likes 생성후 USER에 넣고  저장
        Likes like = new Likes(member);
        likesRepository.save(like);
        return new ResponseEntity<>("좋아요 등록 성공", HttpStatus.valueOf(200));
    }

    //유저의 평가를 잘못 눌렀을 취소 기능
    public ResponseEntity<?> deleteLikes(Long userId) {
        // USERID 로 좋아요 한 게시물들을 리스트에 담아서
        List<Likes> likes = likesRepository.findByUserId(userId);
        //for문으로 앞단에서 받아온 userId 와 같은 좋아요 삭제
        for (Likes like : likes) {
            if (like.getMember().getId().equals(userId)) {
                likesRepository.deleteById(like.getId());
            }
        }
        return new ResponseEntity<>("좋아요 삭제 성공", HttpStatus.valueOf(200));
    }
}
