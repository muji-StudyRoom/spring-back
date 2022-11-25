package dev.kakao5.eyestalkdb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다."),
    ROOM_NOT_FOUND(NOT_FOUND, "해당 방 정보를 찾을 수 없습니다."),
    ROOM_IS_EMPTY(NOT_FOUND, "존재하는 방이 없습니다."),
    INVALID_PASSWORD(BAD_REQUEST, "password_error"),
    FULL_CAPACITY(UNAUTHORIZED, "capacity_error"),
    INVALID_NICKNAME(BAD_REQUEST, "nickname_error"),
    DUPLICATE_RESOURCE(CONFLICT, "이미 존재하는 방입니다.")
            ;


    private final HttpStatus httpStatus;
    private final String detail;

}
