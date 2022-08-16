package com.example.modiraa.LikeAndHate.model;

import com.example.modiraa.loginAndRegister.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Hates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HATES_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private Member member;

    public Hates(Member member) {
        this.member = member;

    }
}