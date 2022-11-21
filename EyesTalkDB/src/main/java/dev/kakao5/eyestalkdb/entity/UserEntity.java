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

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_create_at")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime userCreateAt;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = RoomEntity.class
    )
    @JoinColumn(name = "room_id")
    private RoomEntity roomEntity;

    protected UserEntity() {
    }

    @Builder
    public UserEntity(Long userId, String userNickname, LocalDateTime userCreateAt, RoomEntity roomEntity) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userCreateAt= userCreateAt;
        this.roomEntity= roomEntity;
    }
}
