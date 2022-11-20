package dev.kakao5.eyestalkdb.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long room_id;
    @Column(name = "room_name", length = 100, unique = true)
    private String room_name;
    @Column(name = "room_password", length = 100)
    private String room_password;
    private int room_capacity; //지정 인원
    private int room_enter_user; //입장한 인원

    @CreatedDate
    private LocalDateTime room_create_at;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = UserEntity.class
    )
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    protected RoomEntity() {
    }

    @Builder
    public RoomEntity(Long room_id, String room_name, String room_password, int room_capacity, int room_enter_user, UserEntity userEntity, LocalDateTime room_create_at ) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_password = room_password;
        this.room_capacity = room_capacity;
        this.room_enter_user = room_enter_user;
        this.userEntity = userEntity;
        this.room_create_at=room_create_at;
    }


}
