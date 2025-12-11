package com.example.umc.domain.review.exception.code;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    // 9주차 워크북
    FOUND(HttpStatus.FOUND, "REVIEW302_1", "성공적으로 리뷰를 찾았습니다."),

    // 8주차 미션
    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰 작성에 성공했습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
