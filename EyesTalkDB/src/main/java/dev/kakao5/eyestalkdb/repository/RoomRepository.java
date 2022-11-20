package dev.kakao5.eyestalkdb.repository;

import dev.kakao5.eyestalkdb.entity.RoomEntity;
import dev.kakao5.eyestalkdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
