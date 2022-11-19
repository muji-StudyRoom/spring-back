package dev.kakao5.eyestalkdb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {
    private final Long room_id;
    private final String room_name;
    private final String room_password;
    private final int room_capacity;
    private final int room_enter_user;

    @Builder
    public RoomDto(Long room_id, String room_name, String room_password, int room_capacity, int room_enter_user) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_password = room_password;
        this.room_capacity = room_capacity;
        this.room_enter_user = room_enter_user;
    }
}
