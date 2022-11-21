package dev.kakao5.eyestalkdb.repository;

import dev.kakao5.eyestalkdb.dto.RoomDto;
import dev.kakao5.eyestalkdb.entity.RoomEntity;
import dev.kakao5.eyestalkdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    RoomEntity findByRoomName(String room_name);
}
