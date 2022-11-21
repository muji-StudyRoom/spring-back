package dev.kakao5.eyestalkdb.repository;

import dev.kakao5.eyestalkdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserNickname(String useNickname);
    boolean existsByUserNickname(String userNickname);
}
