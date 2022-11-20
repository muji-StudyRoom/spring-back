package dev.kakao5.eyestalkdb.service;

import dev.kakao5.eyestalkdb.dto.RoomDto;
import dev.kakao5.eyestalkdb.entity.RoomEntity;
import dev.kakao5.eyestalkdb.exception.CustomException;
import dev.kakao5.eyestalkdb.exception.ErrorCode;
import dev.kakao5.eyestalkdb.repository.RoomRepository;
import dev.kakao5.eyestalkdb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = false)
public class RoomServiceImpl implements RoomServiceInterface {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public RoomDto createRoom(RoomDto dto) {
        RoomEntity createRoom = RoomEntity.builder()
                .room_name(dto.getRoom_name())
                .room_password(dto.getRoom_password())
                .room_capacity(dto.getRoom_capacity())
                .room_enter_user(1)
                .room_create_at(LocalDateTime.now())
                .build();

        RoomEntity save = roomRepository.save(createRoom);
        RoomDto roomDto = RoomDto.builder()
                .room_id(createRoom.getRoomId())
                .room_name(createRoom.getRoom_name())
                .room_password(createRoom.getRoom_password())
                .room_capacity(createRoom.getRoom_capacity())
                .room_enter_user(createRoom.getRoom_enter_user())
                .room_create_at(createRoom.getRoom_create_at())
                .build();

        return roomDto;
    }

    @Override
    public boolean deleteRoom(Long roomId) {
        if(!roomRepository.existsById(roomId)){
            throw new CustomException(ErrorCode.ROOM_NOT_FOUND);
        }
        Optional<RoomEntity> roomEntity = roomRepository.findById(roomId);
        roomRepository.delete(roomEntity.get());
        return true;
    }

    @Override
    public List<RoomDto> getAllRoom() {
        if(roomRepository.findAll().isEmpty()){
            throw new CustomException(ErrorCode.ROOM_IS_EMPTY);
        }

        return roomRepository.findAll().stream()
                .map(roomEntity -> RoomDto.builder()
                        .room_id(roomEntity.getRoomId())
                        .room_name(roomEntity.getRoom_name())
                        .room_password(roomEntity.getRoom_password())
                        .room_capacity(roomEntity.getRoom_capacity())
                        .room_enter_user(roomEntity.getRoom_enter_user())
                        .room_create_at(roomEntity.getRoom_create_at())
                        .build())
                .collect(Collectors.toList());
    }
//
//    @Override
//    public List<RoomDto> searchRoom(String room_name) {
//
//    }
}
