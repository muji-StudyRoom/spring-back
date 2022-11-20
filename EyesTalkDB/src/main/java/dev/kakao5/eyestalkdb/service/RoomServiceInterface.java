package dev.kakao5.eyestalkdb.service;

import dev.kakao5.eyestalkdb.dto.RoomDto;
import dev.kakao5.eyestalkdb.dto.UserDto;

import java.util.List;

public interface RoomServiceInterface {

    // 방 생성
    RoomDto createRoom(RoomDto dto);

//    // 방 삭제
//    boolean deleteRoom(Long roomId);
//
//    // 전체 방 조회
//    List<RoomDto> getAllRoom();
//
//    // 방 검색
//    List<RoomDto> searchRoom(String room_name);

}
