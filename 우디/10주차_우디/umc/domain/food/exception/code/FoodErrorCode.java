package com.example.umc.domain.food.exception.code;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD404_1", "해당 음식을 찾지 못했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
