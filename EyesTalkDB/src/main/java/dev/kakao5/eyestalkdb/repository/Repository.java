package dev.kakao5.eyestalkdb.repository;

import dev.kakao5.eyestalkdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface Repository extends JpaRepository<UserEntity, Long> {

}
