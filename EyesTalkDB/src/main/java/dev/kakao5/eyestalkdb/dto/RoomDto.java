package dev.kakao5.eyestalkdb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {
    private final Long roomId;
    private final String roomName;
    private final String roomPassword;
    private final int roomCapacity;
    private final int roomEnterUser;
    private final LocalDateTime roomCreateAt;

    @Builder
    public RoomDto(Long roomId, String roomName, String roomPassword, int roomCapacity, int roomEnterUser, LocalDateTime roomCreateAt) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomPassword = roomPassword;
        this.roomCapacity = roomCapacity;
        this.roomEnterUser = roomEnterUser;
        this.roomCreateAt= roomCreateAt;
    }
}
