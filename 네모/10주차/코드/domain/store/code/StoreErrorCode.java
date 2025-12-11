package com.example.umc9th_week5.domain.store.code;

import com.example.umc9th_week5.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "STORE400_1", "필수 값이 요청되지 않았습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "STORE400_2", "해당 가게를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
