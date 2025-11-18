package com.example.umc_9th_spring.domain.test.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TestSuccessCode implements BaseSuccessCode {

    TEST_SUCCESS(HttpStatus.OK, "TEST200_1", "테스트 요청이 성공적으로 처리되었습니다."),
    TEST_CREATED(HttpStatus.CREATED, "TEST201_1", "테스트 데이터가 성공적으로 생성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
