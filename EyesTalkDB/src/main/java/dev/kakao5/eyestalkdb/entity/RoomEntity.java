package dev.kakao5.eyestalkdb.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Table(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_idx")
    private Long roomId;
    @Column(name = "room_name", length = 100)
    private String roomName;

    @Column(name = "room_password", length = 100)
    private String roomPassword;

    @Column(name = "room_capacity")
    private int roomCapacity; //지정 인원

    @Column(name = "room_enter_user")
    private int roomEnterUser; //입장한 인원

    @Column(name = "room_create_at")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime roomCreateAt;

    protected RoomEntity() {
    }

    public void setRoomEnterUser(int roomEnterUser) {
        this.roomEnterUser = roomEnterUser;
    }

    @Builder
    public RoomEntity(Long roomId, String roomName, String roomPassword, int roomCapacity, int roomEnterUser, UserEntity userEntity, LocalDateTime roomCreateAt ) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomPassword = roomPassword;
        this.roomCapacity = roomCapacity;
        this.roomEnterUser = roomEnterUser;
        this.roomCreateAt=roomCreateAt;
    }


}
