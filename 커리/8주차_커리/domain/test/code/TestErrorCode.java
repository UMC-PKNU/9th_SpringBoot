package com.example.umc_9th_spring.domain.test.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TestErrorCode implements BaseErrorCode {

    TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_1", "테스트 도메인에서 터진 Bad Request 에러입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
