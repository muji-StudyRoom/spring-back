package dev.kakao5.eyestalkdb.controller;

import dev.kakao5.eyestalkdb.dto.RoomDto;
import dev.kakao5.eyestalkdb.exception.CustomException;
import dev.kakao5.eyestalkdb.service.RoomServiceImpl;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController()
@RequestMapping("room")
public class RoomController {
    private final RoomServiceImpl roomService;


//    @PostMapping
//    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto dto){
//        RoomDto result = this.roomService.createRoom(dto);
//        return ResponseEntity.ok(result);
//    }


    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable("roomId") Long roomId){
        if(!roomService.deleteRoom(roomId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roomId);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRoom(){
        List<RoomDto> roomDtoList = this.roomService.getAllRoom();
        if(roomDtoList == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(roomDtoList);
        }
    }


    @GetMapping("{roomName}")
    public ResponseEntity<List<RoomDto>> searchRoom(@PathVariable("roomName") String room_name){
        List<RoomDto> roomDtoList = this.roomService.searchRoomList(room_name);
        if(roomDtoList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roomDtoList);
    }
    // 방 생성 검증
    @PostMapping("/valid/create")
    public ResponseEntity<Boolean> validationCreateRoom(@RequestBody RoomDto dto){
        try{
            RoomDto roomDto = this.roomService.searchRoom(dto.getRoomName());
        }catch (CustomException e){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
