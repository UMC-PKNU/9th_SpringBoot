package com.example.umc.domain.inquiry.exception.code;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InquiryErrorCode implements BaseErrorCode {

    INQUIRY_NOT_FOUND(HttpStatus.NOT_FOUND, "INQUIRY404_1", "해당 ID의 문의를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
