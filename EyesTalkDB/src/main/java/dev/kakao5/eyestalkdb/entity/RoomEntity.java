package dev.kakao5.eyestalkdb.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long room_id;
    private String room_name;
    private String room_password;
    private int room_capacity;
    private int room_enter_user;
    //todo : 생성시간

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = UserEntity.class
    )
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    protected RoomEntity() {
    }

    @Builder
    public RoomEntity(Long room_id, String room_name, String room_password, int room_capacity, int room_enter_user, UserEntity userEntity) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_password = room_password;
        this.room_capacity = room_capacity;
        this.room_enter_user = room_enter_user;
        this.userEntity = userEntity;
    }


}
