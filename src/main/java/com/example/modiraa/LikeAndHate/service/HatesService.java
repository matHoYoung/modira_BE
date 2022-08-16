package com.example.modiraa.LikeAndHate.service;

import com.example.modiraa.LikeAndHate.model.Hates;
import com.example.modiraa.LikeAndHate.repository.HatesRepository;
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
public class HatesService {
    private  final HatesRepository hatesRepository;
    private  final UserRepository userRepository;

    //유저의 평가 점수 -1점 부여하고 싶을때
    public ResponseEntity<?> userHates(Long userId) {
        //USERID 아이디로 USER 를 찾아서 저장
        Member member = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저가 없습니다"));
        Optional<Hates> Hatefound = hatesRepository.findByMember(member);
        //이미 있는 USER가 들어오면 오류 메시지 전달
        if(Hatefound.isPresent()){
            return new ResponseEntity<>("이미 평가를 하셨습니다.", HttpStatus.valueOf(400));
        }
        //새로운  Hates 생성후 USER에 넣고  저장
        Hates hates = new Hates(member);
        hatesRepository.save(hates);
        return new ResponseEntity<>("싫어요 등록 성공", HttpStatus.valueOf(200));
    }

    //유저의 평가를 잘못 눌렀을 취소 기능
    public ResponseEntity<?> deleteHates(Long userId) {
        // USERID 로 싫어요 한 게시물들을 리스트에 담아서
        List<Hates> hates = hatesRepository.findByUserId(userId);
        //for문으로 앞단에서 받아온 userId 와 같은 싫어요 삭제
        for (Hates hate : hates) {
            if (hate.getMember().getId().equals(userId)) {
                hatesRepository.deleteById(hate.getId());
            }
        }
        return new ResponseEntity<>("싫어요 삭제 성공", HttpStatus.valueOf(200));

    }
}