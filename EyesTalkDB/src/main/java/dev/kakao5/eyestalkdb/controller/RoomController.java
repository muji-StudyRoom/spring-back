package dev.kakao5.eyestalkdb.controller;

import dev.kakao5.eyestalkdb.dto.RoomDto;
import dev.kakao5.eyestalkdb.service.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController()
@RequestMapping("room")
public class RoomController {
    private final RoomServiceImpl roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto dto){
        RoomDto result = this.roomService.createRoom(dto);
        return ResponseEntity.ok(result);
    }
}
