package dev.kakao5.eyestalkdb.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Table(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;
    @Column(name = "room_name", length = 100)
    private String room_name;
    @Column(name = "room_password", length = 100)
    private String room_password;
    private int room_capacity; //지정 인원
    private int room_enter_user; //입장한 인원

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime room_create_at;

    protected RoomEntity() {
    }

    @Builder
    public RoomEntity(Long room_id, String room_name, String room_password, int room_capacity, int room_enter_user, UserEntity userEntity, LocalDateTime room_create_at ) {
        this.roomId = room_id;
        this.room_name = room_name;
        this.room_password = room_password;
        this.room_capacity = room_capacity;
        this.room_enter_user = room_enter_user;
        this.room_create_at=room_create_at;
    }


}
