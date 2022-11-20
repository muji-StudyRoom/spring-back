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
                .user_nickname(dto.getUser_nickname())
                .user_create_at(LocalDateTime.now())
                .build();

        UserEntity save = userRepository.save(createUser);
        UserDto userDto = UserDto.builder()
                .user_id(createUser.getUserId())
                .user_nickname(createUser.getUser_nickname())
                .user_create_at(createUser.getUser_create_at())
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
