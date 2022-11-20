package dev.kakao5.eyestalkdb.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_nickname", length = 50, unique = true)
    private String user_nickname;

    @CreatedDate
    private LocalDateTime user_create_at;

    protected UserEntity() {
    }

    @Builder
    public UserEntity(Long userId, String user_nickname, LocalDateTime user_create_at) {
        this.userId = userId;
        this.user_nickname = user_nickname;
        this.user_create_at= user_create_at;
    }
}
