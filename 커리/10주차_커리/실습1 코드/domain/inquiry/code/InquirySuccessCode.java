package com.example.umc_9th_spring.domain.inquiry.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum InquirySuccessCode implements BaseSuccessCode {
    INQUIRY_CREATE_SUCCESS(HttpStatus.CREATED, "INQUIRY201_1", "문의가 성공적으로 등록되었습니다."),
    INQUIRY_RESPONSE_SUCCESS(HttpStatus.OK, "INQUIRY200_1", "문의에 대한 답변이 성공적으로 전송되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
