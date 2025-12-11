package com.example.umc9th_week5.domain.review.code;

import com.example.umc9th_week5.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "REVIEW400_1", "필수 값이 요청되지 않았습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW400_2", "해당 리뷰를 찾을 수 없습니다."),
    INVALID_PAGE_SIZE(HttpStatus.BAD_REQUEST, "REVIEW400_3", "페이지가 범위를 벗어났습니다.");

    private HttpStatus status;
    private String code;
    private String message;
}
