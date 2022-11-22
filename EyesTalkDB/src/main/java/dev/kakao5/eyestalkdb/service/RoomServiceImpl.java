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
@Transactional
public class RoomServiceImpl implements RoomServiceInterface {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public RoomDto createRoom(RoomDto dto) {
        RoomEntity createRoom = RoomEntity.builder()
                .roomName(dto.getRoomName())
                .roomPassword(dto.getRoomPassword())
                .roomCapacity(dto.getRoomCapacity())
                .roomEnterUser(1)
                .roomCreateAt(LocalDateTime.now())
                .build();

        RoomEntity save = roomRepository.save(createRoom);
        RoomDto roomDto = RoomDto.builder()
                .roomId(createRoom.getRoomId())
                .roomName(createRoom.getRoomName())
                .roomPassword(createRoom.getRoomPassword())
                .roomCapacity(createRoom.getRoomCapacity())
                .roomEnterUser(createRoom.getRoomEnterUser())
                .roomCreateAt(createRoom.getRoomCreateAt())
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
    @Transactional(readOnly = true)
    public List<RoomDto> getAllRoom() {
        if(roomRepository.findAll().isEmpty()){
            throw new CustomException(ErrorCode.ROOM_IS_EMPTY);
        }

        return roomRepository.findAll().stream()
                .map(roomEntity -> RoomDto.builder()
                        .roomId(roomEntity.getRoomId())
                        .roomName(roomEntity.getRoomName())
                        .roomPassword(roomEntity.getRoomPassword())
                        .roomCapacity(roomEntity.getRoomCapacity())
                        .roomEnterUser(roomEntity.getRoomEnterUser())
                        .roomCreateAt(roomEntity.getRoomCreateAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RoomDto searchRoom(String room_name) {
        RoomEntity findRoom = roomRepository.findByRoomName(room_name);
        if(findRoom == null){
            throw new CustomException(ErrorCode.ROOM_IS_EMPTY);
        }

        RoomDto roomDto = RoomDto.builder()
                .roomId(findRoom.getRoomId())
                .roomName(findRoom.getRoomName())
                .roomPassword(findRoom.getRoomPassword())
                .roomCapacity(findRoom.getRoomCapacity())
                .roomEnterUser(findRoom.getRoomEnterUser())
                .roomCreateAt(findRoom.getRoomCreateAt())
                .build();
        return roomDto;
    }
}