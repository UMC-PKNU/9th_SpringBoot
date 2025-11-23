package com.example.umc9th_week5.domain.review.code;

import com.example.umc9th_week5.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "REVIEW200_1", "해당 요청을 성공적으로 처리했습니다.");

    private HttpStatus status;
    private String code;
    private String message;
}
