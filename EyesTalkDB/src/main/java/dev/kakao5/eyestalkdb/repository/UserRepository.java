package dev.kakao5.eyestalkdb.repository;

import dev.kakao5.eyestalkdb.dto.RoomDto;
import dev.kakao5.eyestalkdb.entity.RoomEntity;
import dev.kakao5.eyestalkdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserNickname(String useNickname);
    boolean existsByUserNickname(String userNickname);

    UserEntity findUserEntityByRoomEntityAndUserNickname(RoomEntity room, String userNickname);

}
