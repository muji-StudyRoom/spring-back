package dev.kakao5.eyestalkdb.controller;


import dev.kakao5.eyestalkdb.dto.CommonDto;
import dev.kakao5.eyestalkdb.dto.UserDto;
import dev.kakao5.eyestalkdb.service.CommonService;
import dev.kakao5.eyestalkdb.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        logger.info("controller-resultRoom 방 허용 입력값: {}", resultRoom.getRoomCapacity());

        CommonDto result  = this.commonService.createUser(resultRoom);
        logger.info("controller-result 방 허용 입력값: {}", result.getRoomCapacity());
        return ResponseEntity.ok(result);
    }


    //방 나가기



    // 방 입장 + 유저 생성
    @PostMapping("/{roomId}/enter/{roomPassword}")
    public ResponseEntity<CommonDto> enterRoom(@RequestBody UserDto dto,
                                               @PathVariable("roomId") Long roomId,
                                               @PathVariable("roomPassword") String roomPassword ){
        CommonDto result =  this.commonService.enterRoom(dto, roomId, roomPassword);
        return ResponseEntity.ok(result);
    }

}
