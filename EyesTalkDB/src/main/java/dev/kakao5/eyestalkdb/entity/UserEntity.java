package dev.kakao5.eyestalkdb.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "userTable")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;



}
