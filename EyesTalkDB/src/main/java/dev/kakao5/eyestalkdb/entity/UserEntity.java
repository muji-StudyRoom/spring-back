package dev.kakao5.eyestalkdb.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

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
    private String user_nickname;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime user_create_at;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = RoomEntity.class
    )
    @JoinColumn(name = "room_id")
    private RoomEntity roomEntity;

    protected UserEntity() {
    }

    @Builder
    public UserEntity(Long userId, String user_nickname, LocalDateTime user_create_at, RoomEntity roomEntity) {
        this.userId = userId;
        this.user_nickname = user_nickname;
        this.user_create_at= user_create_at;
        this.roomEntity= roomEntity;
    }
}
