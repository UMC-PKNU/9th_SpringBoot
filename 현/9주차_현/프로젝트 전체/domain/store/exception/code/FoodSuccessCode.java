package com.example.umc_9th_.domain.store.exception.code;

import com.example.umc_9th_.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodSuccessCode implements BaseSuccessCode {

    // 200 OK
    FOOD_CHECK(HttpStatus.OK, "STORE200_1", "카테고리 조회가 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}