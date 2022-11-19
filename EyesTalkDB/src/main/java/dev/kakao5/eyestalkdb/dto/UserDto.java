package dev.kakao5.eyestalkdb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private final Long user_id;
    private final String user_nickname;
    //todo: 생성시간

    @Builder
    public UserDto(Long user_id, String user_nickname) {
        this.user_id = user_id;
        this.user_nickname = user_nickname;
    }


}
