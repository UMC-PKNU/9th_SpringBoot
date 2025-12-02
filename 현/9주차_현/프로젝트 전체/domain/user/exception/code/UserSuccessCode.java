package com.example.umc_9th_.domain.user.exception.code;

import com.example.umc_9th_.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "USER200_1",
            "성공적으로 사용자를 조회했습니다."),

    USER_JOIN_SUCCESS(HttpStatus.CREATED, "USER2011", "회원가입이 성공적으로 완료되었습니다."),

    USER_LOCATION_UPDATE_SUCCESS(HttpStatus.OK, "USER2001", "사용자 위치가 성공적으로 수정되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}