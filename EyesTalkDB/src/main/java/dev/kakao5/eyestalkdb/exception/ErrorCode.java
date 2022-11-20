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
    INVALID_PASSWORD(BAD_REQUEST, "잘못된 비밀번호입니다."),
    FULL_CAPACITY(UNAUTHORIZED, "인원 초과입니다."),
    INVALID_NICKNAME(BAD_REQUEST, "이미 사용 중인 닉네임입니다.")

            ;


    private final HttpStatus httpStatus;
    private final String detail;

}
