package dev.kakao5.eyestalkdb.controller;

import dev.kakao5.eyestalkdb.dto.UserDto;
import dev.kakao5.eyestalkdb.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController()
@RequestMapping("user")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/{roomName}")
    public ResponseEntity<List<UserDto>> getUserInRoom(@PathVariable String roomName) {
        System.out.println(roomName);
        return ResponseEntity.ok(userService.getUserInRoom(roomName));
    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto){
        UserDto result = this.userService.createUser(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId){
       if (!userService.deleteUser(userId))
           return ResponseEntity.notFound().build();
       return ResponseEntity.ok(userId);
    }







}
