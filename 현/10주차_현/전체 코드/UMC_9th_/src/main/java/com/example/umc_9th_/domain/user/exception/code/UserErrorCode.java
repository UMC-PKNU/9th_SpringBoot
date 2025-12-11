package com.example.umc_9th_.domain.user.exception.code;

import com.example.umc_9th_.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "User404_1",
            "해당 사용자를 찾지 못했습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST,
            "User400_1",
            "유효하지 않은 패스워드입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
