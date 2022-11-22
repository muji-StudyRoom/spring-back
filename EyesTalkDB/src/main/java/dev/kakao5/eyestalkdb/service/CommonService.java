package dev.kakao5.eyestalkdb.service;

import dev.kakao5.eyestalkdb.dto.CommonDto;
import dev.kakao5.eyestalkdb.dto.UserDto;
import dev.kakao5.eyestalkdb.entity.RoomEntity;
import dev.kakao5.eyestalkdb.entity.UserEntity;
import dev.kakao5.eyestalkdb.exception.CustomException;
import dev.kakao5.eyestalkdb.exception.ErrorCode;
import dev.kakao5.eyestalkdb.repository.RoomRepository;
import dev.kakao5.eyestalkdb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(CommonService.class);

    // room 생성
    public CommonDto createRoom(CommonDto dto) {
        //room이 있는지 확인
        if (roomRepository.existsByRoomName(dto.getRoomName())) {
            throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
        }

//        if (userRepository.existsByUserNickname(dto.getUserNickname())){
//            throw new CustomException(ErrorCode.INVALID_NICKNAME);
//        }

        UserEntity duplicateUser = userRepository.findByUserNickname(dto.getUserNickname());

        //없으면 room 생성
        RoomEntity createRoom = RoomEntity.builder()
                .roomName(dto.getRoomName())
                .roomPassword(dto.getRoomPassword())
                .roomCapacity(dto.getRoomCapacity())
                .roomEnterUser(1)
                .roomCreateAt(LocalDateTime.now())
                .build();


        RoomEntity roomSave = roomRepository.save(createRoom);

        if(duplicateUser!=null){
            System.out.println("로그 :"+duplicateUser.getRoomEntity().getRoomId()+ " "+ createRoom.getRoomId());
        }

        if(duplicateUser!=null && duplicateUser.getRoomEntity().getRoomId().equals(createRoom.getRoomId())){
            //지워야 함 (room)
            roomRepository.delete(createRoom);
            throw new CustomException(ErrorCode.INVALID_NICKNAME);
        }

        logger.info("방 허용 입력값: {}", createRoom.getRoomCapacity());
        logger.info("방 입장인원 입력값: {}", createRoom.getRoomEnterUser());

        CommonDto commonDto = CommonDto.builder()
                .roomName(createRoom.getRoomName())
                .roomId(createRoom.getRoomId())
                .roomPassword(createRoom.getRoomPassword())
                .roomCreateAt(createRoom.getRoomCreateAt())
                .roomCapacity(createRoom.getRoomCapacity())
                .roomEnterUser(createRoom.getRoomEnterUser())
                .userNickname(dto.getUserNickname())
                .build();
        logger.info("방 허용 저장값: {}", commonDto.getRoomCapacity());
        logger.info("방 입장인원 저장값: {}", commonDto.getRoomEnterUser());

        return commonDto;
    }

    // room 생성 시 user 생성
    public CommonDto createUser(CommonDto dto){

        // 닉네임 중복 검사
        UserEntity duplicateUser = userRepository.findByUserNickname(dto.getUserNickname());
        if(duplicateUser!=null && duplicateUser.getRoomEntity().getRoomId().equals(dto.getRoomId())){
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
                .roomCapacity(room.getRoomCapacity())
                .roomEnterUser(room.getRoomEnterUser())
                .build();
        return dto;
    }

    // room enter 시 user 생성
    public CommonDto enterRoom(UserDto dto, Long roomId,String roomPassword){
        // room id 확인
        if (!roomRepository.existsById(roomId))
            throw new CustomException(ErrorCode.ROOM_IS_EMPTY);

        // room 찾기
        RoomEntity room = roomRepository.findById(roomId).get();

        // 비밀번호 확인
        if(!room.getRoomPassword().equals(roomPassword)){
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // 닉네임 중복 검사
        UserEntity duplicateUser = userRepository.findByUserNickname(dto.getUserNickname());
        if(duplicateUser!=null && duplicateUser.getRoomEntity().getRoomId().equals(roomId)){
            throw new CustomException(ErrorCode.INVALID_NICKNAME);
        }

        // 인원 검사
        if(room.getRoomCapacity() == room.getRoomEnterUser()){
            throw new CustomException(ErrorCode.FULL_CAPACITY);
        }

        // 유저 생성
        UserEntity createUser = UserEntity.builder()
                .userNickname(dto.getUserNickname())
                .userCreateAt(LocalDateTime.now())
                .roomEntity(room)
                .build();

        UserEntity save = userRepository.save(createUser);
        room.setRoomEnterUser(room.getRoomEnterUser()+1);

        CommonDto result = CommonDto.builder()
                .userId(createUser.getUserId())
                .userNickname(createUser.getUserNickname())
                .userCreateAt(createUser.getUserCreateAt())
                .roomId(room.getRoomId())
                .roomPassword(room.getRoomPassword())
                .roomCapacity(room.getRoomCapacity())
                .roomEnterUser(room.getRoomEnterUser())
                .roomCreateAt(room.getRoomCreateAt())
                .build();

        return result;
    }


    //방 나가기 + 방 삭제
    public boolean closeRoom(Long roomId, Long userId){
        //todo: 코드 수정 필요
        Optional<RoomEntity> room = roomRepository.findById(roomId);
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        // roomId check
        if (!roomRepository.existsById(roomId))
            throw new CustomException(ErrorCode.ROOM_IS_EMPTY);

        // userId check
        if(!userRepository.existsById(userId))
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);

        // 인원 검사 {지정인원이 0이면 방 삭제, 허용인원보다 적고 0이 아니면 -1}
        if(room.get().getRoomEnterUser() > 1){
            //유저만 삭제 , 방 정보 변경
            room.get().setRoomEnterUser(room.get().getRoomEnterUser()-1);
            RoomEntity updateRoom = RoomEntity.builder()
                    .roomId(roomId)
                    .roomName(room.get().getRoomName())
                    .roomCapacity(room.get().getRoomCapacity())
                    .roomPassword(room.get().getRoomPassword())
                    .roomEnterUser(room.get().getRoomEnterUser())
                    .roomCreateAt(room.get().getRoomCreateAt())
                    .build();
            RoomEntity roomSave = roomRepository.save(updateRoom);
            userRepository.delete(userEntity.get());
        }else{
            //유저 삭제, 방 삭제 동시 진행
            roomRepository.delete(room.get());
            userRepository.delete(userEntity.get());
        }
        return true;
    }


}
