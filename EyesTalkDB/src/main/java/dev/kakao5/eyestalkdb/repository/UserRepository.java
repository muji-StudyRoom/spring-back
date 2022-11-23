package dev.kakao5.eyestalkdb.repository;

import dev.kakao5.eyestalkdb.dto.RoomDto;
import dev.kakao5.eyestalkdb.entity.RoomEntity;
import dev.kakao5.eyestalkdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserNickname(String useNickname);
    boolean existsByUserNickname(String userNickname);

    UserEntity findUserEntityByRoomEntityAndUserNickname(RoomEntity room, String userNickname);

    @Query("select u from UserEntity u join u.roomEntity r where r.roomId = :roomId")
    List<UserEntity> findAllByRoomEntity(Long roomId);

}
