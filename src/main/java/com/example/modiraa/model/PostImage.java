package com.example.modiraa.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class PostImage {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String menu;

    @Column(nullable = false)
    private String imageurl;

}
