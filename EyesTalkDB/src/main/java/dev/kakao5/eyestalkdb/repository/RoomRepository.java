package dev.kakao5.eyestalkdb.repository;

import dev.kakao5.eyestalkdb.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    RoomEntity findByRoomName(String roomName);
    boolean existsByRoomName(String roomName);


}
