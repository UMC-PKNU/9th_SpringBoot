package com.example.umc_9th_spring.domain.inquiry.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum InquiryErrorCode implements BaseErrorCode {
    INQUIRY_NOT_FOUND(HttpStatus.NOT_FOUND, "INQUIRY404_1", "문의 내역을 찾을 수 없습니다."),
    INQUIRY_SAVE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "INQUIRY500_1", "문의 저장에 실패했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
