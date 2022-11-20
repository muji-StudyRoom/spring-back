package dev.kakao5.eyestalkdb.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String user_nickname;

    protected UserEntity() {
    }

    @Builder
    public UserEntity(Long userId, String user_nickname) {
        this.userId = userId;
        this.user_nickname = user_nickname;
    }
}
