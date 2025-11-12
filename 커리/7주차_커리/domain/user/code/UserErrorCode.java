package com.example.umc_9th_spring.domain.user.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_EXCEPTION(HttpStatus.BAD_REQUEST, "USER400_1", "User 도메인에서 터진 Bad Request 에러입니다."),
    USER_INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST, "USER400", "잘못된 미션 상태입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "해당 유저를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
