package com.example.umc_9th_spring.domain.review.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "리뷰를 찾을 수 없습니다."),
    REVIEW_SAVE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "REVIEW500_1", "리뷰 저장에 실패했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
