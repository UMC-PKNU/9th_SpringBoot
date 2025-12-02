package com.example.umc_9th_.domain.review.exception.code;

import com.example.umc_9th_.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_REGISTER(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 성공적으로 등록되었습니다."),
    MY_REVIEW_LIST(HttpStatus.OK, "REVIEW200_1", "나의 리뷰 목록 조회가 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}