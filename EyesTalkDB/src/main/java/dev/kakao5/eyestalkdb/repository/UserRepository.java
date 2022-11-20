package dev.kakao5.eyestalkdb.repository;

import dev.kakao5.eyestalkdb.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
