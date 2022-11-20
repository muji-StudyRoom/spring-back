package dev.kakao5.eyestalkdb.service;


import dev.kakao5.eyestalkdb.dto.UserDto;

public interface UserServiceInterface {

    // 유저 생성
    UserDto createUser(UserDto dto);

    // 유저 삭제
    boolean deleteUser(Long userId);
}
