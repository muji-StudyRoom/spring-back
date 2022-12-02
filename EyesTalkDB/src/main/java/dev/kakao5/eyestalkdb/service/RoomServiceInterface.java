package dev.kakao5.eyestalkdb.service;

import dev.kakao5.eyestalkdb.dto.RoomDto;


import java.util.List;

public interface RoomServiceInterface {

    // 방 생성
    RoomDto createRoom(RoomDto dto);

    // 방 삭제
    boolean deleteRoom(Long roomId);

    // 전체 방 조회
    List<RoomDto> getAllRoom();

    // 방 검색
    RoomDto searchRoom(String room_name);
    List<RoomDto> searchRoomList(String room_name);
}
