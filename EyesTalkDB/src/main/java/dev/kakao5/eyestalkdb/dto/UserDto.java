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
    private final Long userId;
    private final String userNickname;
    private final LocalDateTime userCreateAt;

    @Builder
    public UserDto(Long userId, String userNickname, LocalDateTime userCreateAt) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userCreateAt= userCreateAt;
    }


}
