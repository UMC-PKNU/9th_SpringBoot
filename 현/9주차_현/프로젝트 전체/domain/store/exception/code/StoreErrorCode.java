package com.example.umc_9th_.domain.store.exception.code;

import com.example.umc_9th_.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    // 가게 관련 에러
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "해당 가게를 찾을 수 없습니다."),

    // 지역 관련 에러
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION404_1", "해당 지역을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}