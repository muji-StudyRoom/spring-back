package dev.kakao5.eyestalkdb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonDto {
    private final Long user_id;
    private final String user_nickname;
    private final LocalDateTime user_create_at;

    private final Long room_id;
    private final String room_name;
    private final String room_password;
    private final int room_capacity;
    private final int room_enter_user;
    private final LocalDateTime room_create_at;

    @Builder
    public CommonDto(Long user_id, String user_nickname, LocalDateTime user_create_at, Long room_id, String room_name, String room_password, int room_capacity, int room_enter_user, LocalDateTime room_create_at) {
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_create_at = user_create_at;
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_password = room_password;
        this.room_capacity = room_capacity;
        this.room_enter_user = room_enter_user;
        this.room_create_at = room_create_at;
    }
}
