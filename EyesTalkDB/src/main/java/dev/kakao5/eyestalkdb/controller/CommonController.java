package dev.kakao5.eyestalkdb.controller;


import dev.kakao5.eyestalkdb.dto.CommonDto;
import dev.kakao5.eyestalkdb.dto.RoomDto;
import dev.kakao5.eyestalkdb.dto.UserDto;
import dev.kakao5.eyestalkdb.exception.CustomException;
import dev.kakao5.eyestalkdb.exception.ErrorResponse;
import dev.kakao5.eyestalkdb.service.CommonService;
import dev.kakao5.eyestalkdb.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Socket;

@RequiredArgsConstructor
@RestController()
@RequestMapping("room")
public class CommonController {

    private final CommonService commonService;

    Logger logger = LoggerFactory.getLogger(CommonController.class);

    private final UserServiceImpl userService;

    //방 생성 + 유저 생성
    @PostMapping
    public ResponseEntity<CommonDto> createRoom(@RequestBody CommonDto dto){
        CommonDto resultRoom = this.commonService.createRoom(dto);
        CommonDto result  = this.commonService.createUser(resultRoom);
        return ResponseEntity.ok(result);
    }

    // 방 입장 + 유저 생성
    @PostMapping("/{roomId}/enter/{roomPassword}")
    public ResponseEntity<CommonDto> enterRoom(@RequestBody UserDto dto,
                                               @PathVariable("roomId") Long roomId,
                                               @PathVariable("roomPassword") String roomPassword ){
        CommonDto result =  this.commonService.enterRoom(dto, roomId, roomPassword);
        return ResponseEntity.ok(result);
    }

    // 방 입장 검증 => 이후 exception 으로 리펙토링 해야할듯
    @PostMapping("/valid/enter")
    public ResponseEntity validationJoinRoom(@RequestBody CommonDto dto){

        logger.info("recive");
        Boolean result= true ;
        try{
            result =  this.commonService.validationJoinRoom(dto, dto.getRoomId(), dto.getRoomPassword());
            System.out.println(ResponseEntity.ok(result));

        }catch(CustomException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e);

            return ResponseEntity.badRequest().body(e.getErrorCode().getDetail());
        }
        return ResponseEntity.ok().build();
    }

    // 방 나가기 + 방 삭제 => socketId 로 로직처리하도록 실행
    @PostMapping("/test")
    public ResponseEntity<?> closeRoom(@RequestParam String socketId ){
        return ResponseEntity.ok(commonService.closeRoom(socketId));
    }
}
