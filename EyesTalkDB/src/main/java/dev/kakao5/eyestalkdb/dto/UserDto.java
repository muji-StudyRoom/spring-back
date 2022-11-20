package dev.kakao5.eyestalkdb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private final Long user_id;
    private final String user_nickname;
    private final LocalDateTime user_create_at;

    @Builder
    public UserDto(Long user_id, String user_nickname, LocalDateTime user_create_at) {
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_create_at= user_create_at;
    }


}
