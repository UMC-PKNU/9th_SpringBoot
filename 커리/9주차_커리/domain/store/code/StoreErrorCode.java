package com.example.umc_9th_spring.domain.store.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "해당 가게를 찾을 수 없습니다."),
    STORE_SAVE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "STORE500_1", "가게 정보 저장에 실패했습니다."),
    LOCATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "LOCATION400_1", "해당 지역이 존재하지 않습니다."),
    STORE_NULL_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "STORE500_2", "null값으로 Store 도메인을 찾아 터진 에러입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
