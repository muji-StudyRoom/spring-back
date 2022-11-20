package dev.kakao5.eyestalkdb.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static dev.kakao5.eyestalkdb.exception.ErrorCode.MEMBER_NOT_FOUND;

@Slf4j
@RestControllerAdvice  //프로젝트 전역에서 발생하는 모든 예외
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //특정 예외를 잡음
    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorResponse> handleDataException() {  // hibernate 관련 에러
        log.error("handleDataException throw Exception : {}", MEMBER_NOT_FOUND);
        return ErrorResponse.toResponseEntity(MEMBER_NOT_FOUND);
    }

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode()); //직접 정의한 CustomException
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
