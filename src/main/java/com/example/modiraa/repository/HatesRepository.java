package com.example.modiraa.repository;

import com.example.modiraa.model.Hates;
import com.example.modiraa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HatesRepository extends JpaRepository<Hates, Long> {
    Optional<Hates> findByGiverAndReceiver(Member giver, Member receiver);

    // 헤잍츠에 리시버가 변수안에 있는 리시버일때만 싫어요를  받아 카운트 센다.
    @Query("select count(h) from Hates h where h.receiver = :receiver ")
    Long hatesCount(@Param("receiver")Member receiver);

}
