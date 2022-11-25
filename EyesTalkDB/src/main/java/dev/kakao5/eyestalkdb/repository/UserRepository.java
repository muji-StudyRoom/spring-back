package dev.kakao5.eyestalkdb.repository;

import dev.kakao5.eyestalkdb.entity.RoomEntity;
import dev.kakao5.eyestalkdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserNickname(String useNickname);


    boolean existsByRoomEntityAndUserNickname(RoomEntity room, String userNickname);
    boolean existsBySocketId(String socketId);

    UserEntity findUserEntityByRoomEntityAndUserNickname(RoomEntity room, String userNickname);

    @Query("select u from UserEntity u join u.roomEntity r where r.roomName = :roomName")
    List<UserEntity> findAllByRoomEntity(String roomName);

    Optional<UserEntity> findBySocketId(String socketId);

}
