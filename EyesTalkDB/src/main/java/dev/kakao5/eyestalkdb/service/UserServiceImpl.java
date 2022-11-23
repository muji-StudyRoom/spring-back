package dev.kakao5.eyestalkdb.service;

import dev.kakao5.eyestalkdb.dto.UserDto;
import dev.kakao5.eyestalkdb.entity.UserEntity;
import dev.kakao5.eyestalkdb.exception.CustomException;
import dev.kakao5.eyestalkdb.exception.ErrorCode;
import dev.kakao5.eyestalkdb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .socketId(dto.getSocketId())
                .build();

        UserEntity save = userRepository.save(createUser);
        UserDto userDto = UserDto.builder()
                .userId(createUser.getUserId())
                .userNickname(createUser.getUserNickname())
                .userCreateAt(createUser.getUserCreateAt())
                .socketId(dto.getSocketId())
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

    @Override
    public List<UserDto> getUserInRoom(Long roomId) {
        List<UserEntity> userEntities = userRepository.findAllByRoomEntity(roomId);

        List<UserDto> userDtoList = userEntities.stream()
                .map(m-> UserDto.builder()
                                .userId(m.getUserId())
                                .userNickname(m.getUserNickname())
                                .userCreateAt(m.getUserCreateAt())
                                .socketId(m.getSocketId())
                                .build()
                ).collect(Collectors.toList());
        return userDtoList;
    }


}