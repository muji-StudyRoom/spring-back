package dev.kakao5.eyestalkdb.service;

import dev.kakao5.eyestalkdb.dto.CommonDto;
import dev.kakao5.eyestalkdb.entity.RoomEntity;
import dev.kakao5.eyestalkdb.entity.UserEntity;
import dev.kakao5.eyestalkdb.repository.RoomRepository;
import dev.kakao5.eyestalkdb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = false)
public class CommonService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public CommonDto createRoom(CommonDto dto) {
        //room이 있는지 확인
        if (roomRepository.findByRoomName(dto.getRoom_name()).isEmpty()){
          //없으면 room 생성
            RoomEntity createRoom = RoomEntity.builder()
                    .room_name(dto.getRoom_name())
                    .room_password(dto.getRoom_password())
                    .room_capacity(dto.getRoom_capacity())
                    .room_enter_user(1)
                    .room_create_at(LocalDateTime.now())
                    .build();
            RoomEntity roomSave = roomRepository.save(createRoom);

            //유저 생성
            UserEntity createUser = UserEntity.builder()
                    .user_nickname(dto.getUser_nickname())
                    .user_create_at(LocalDateTime.now())
                    .roomEntity(RoomEntity.builder()
                            .room_id(dto.getRoom_id())
                            .build())
                    .build();
            UserEntity userSave = userRepository.save(createUser);
        }else{
            // todo: if => 룸 비밀번호 확인 valid 필요
           //있으면 유저 생성하면서 FK로 room_id를 넣기
            UserEntity joinUser = UserEntity.builder()
                    .user_nickname(dto.getUser_nickname())
                    .user_create_at(LocalDateTime.now())
                    .roomEntity(RoomEntity.builder()
                            .room_id(dto.getRoom_id())
                            .build())
                    .build();
            UserEntity userSave = userRepository.save(joinUser);
        }

        //저장된 값을 가져와야하는데 위의 True, False에 따라 달라짐...
        CommonDto commonDto = CommonDto.builder()
                .build();

    return commonDto;
    }


}
