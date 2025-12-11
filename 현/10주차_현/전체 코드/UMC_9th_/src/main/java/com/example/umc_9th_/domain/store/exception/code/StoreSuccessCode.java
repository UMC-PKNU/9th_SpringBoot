package com.example.umc_9th_.domain.store.exception.code;

import com.example.umc_9th_.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    // 201 Created
    STORE_REGISTER(HttpStatus.CREATED, "STORE201_1", "가게가 성공적으로 등록되었습니다."),
    REVIEW_REGISTER(HttpStatus.CREATED, "STORE201_2", "리뷰가 성공적으로 등록되었습니다."),
    MISSION_REGISTER(HttpStatus.CREATED, "STORE201_3", "미션이 성공적으로 등록되었습니다."),
    MISSION_CHALLENGE(HttpStatus.CREATED, "STORE201_4", "미션 도전이 성공적으로 시작되었습니다."),

    // 200 OK
    STORE_CHECK(HttpStatus.OK, "STORE200_1", "가게 정보 조회가 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}