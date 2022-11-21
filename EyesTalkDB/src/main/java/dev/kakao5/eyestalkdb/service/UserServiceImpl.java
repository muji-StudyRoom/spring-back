package dev.kakao5.eyestalkdb.service;

import dev.kakao5.eyestalkdb.dto.UserDto;
import dev.kakao5.eyestalkdb.entity.UserEntity;
import dev.kakao5.eyestalkdb.exception.CustomException;
import dev.kakao5.eyestalkdb.exception.ErrorCode;
import dev.kakao5.eyestalkdb.exception.GlobalExceptionHandler;
import dev.kakao5.eyestalkdb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserServiceInterface{
    private final UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto dto) {
        UserEntity createUser = UserEntity.builder()
                .userNickname(dto.getUserNickname())
                .userCreateAt(LocalDateTime.now())
                .build();

        UserEntity save = userRepository.save(createUser);
        UserDto userDto = UserDto.builder()
                .userId(createUser.getUserId())
                .userNickname(createUser.getUserNickname())
                .userCreateAt(createUser.getUserCreateAt())
                .build();

        return userDto;
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (!userRepository.existsById(userId))
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);

        Optional<UserEntity> userEntity = userRepository.findById(userId);
        userRepository.delete(userEntity.get());
        return true;
    }


}
