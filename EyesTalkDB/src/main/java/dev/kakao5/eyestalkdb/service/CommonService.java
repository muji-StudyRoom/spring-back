package dev.kakao5.eyestalkdb.service;

import dev.kakao5.eyestalkdb.dto.CommonDto;
import dev.kakao5.eyestalkdb.entity.RoomEntity;
import dev.kakao5.eyestalkdb.entity.UserEntity;
import dev.kakao5.eyestalkdb.exception.CustomException;
import dev.kakao5.eyestalkdb.exception.ErrorCode;
import dev.kakao5.eyestalkdb.repository.RoomRepository;
import dev.kakao5.eyestalkdb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = false)
public class CommonService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    // room 생성
    public CommonDto createRoom(CommonDto dto) {
        //room이 있는지 확인
        if (roomRepository.existsByRoomName(dto.getRoomName())) {
            throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
        }

        //없으면 room 생성
        RoomEntity createRoom = RoomEntity.builder()
                .roomName(dto.getRoomName())
                .roomPassword(dto.getRoomPassword())
                .roomCapacity(dto.getRoomCapacity())
                .roomEnterUser(1)
                .roomCreateAt(LocalDateTime.now())
                .build();
        RoomEntity roomSave = roomRepository.save(createRoom);

        CommonDto commonDto = CommonDto.builder()
                .roomName(createRoom.getRoomName())
                .roomId(createRoom.getRoomId())
                .roomPassword(createRoom.getRoomPassword())
                .roomCreateAt(createRoom.getRoomCreateAt())
                .roomCapacity(createRoom.getRoomCapacity())
                .roomEnterUser(createRoom.getRoomEnterUser())
                .userNickname(dto.getUserNickname())
                .build();

        return commonDto;
    }

    // room 생성 시 user 생성
    public CommonDto createUser(CommonDto dto){

        // 닉네임 중복 검사
        if(userRepository.existsByUserNickname(dto.getUserNickname())){
            throw new CustomException(ErrorCode.INVALID_NICKNAME);
        }

        // 유저 생성 (방장)
        RoomEntity room = roomRepository.findById(dto.getRoomId()).get();

        UserEntity createUser = UserEntity.builder()
                .userNickname(dto.getUserNickname())
                .userCreateAt(LocalDateTime.now())
                .roomEntity(room)
                .build();

        UserEntity save = userRepository.save(createUser);

        dto = CommonDto.builder()
                .userId(createUser.getUserId())
                .userNickname(createUser.getUserNickname())
                .userCreateAt(createUser.getUserCreateAt())
                .build();

        return dto;
    }
}
