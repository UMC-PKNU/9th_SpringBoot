package com.example.umc9th_week5.domain.region.code;

import com.example.umc9th_week5.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RegionSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "REGION200_1", "요청이 성공적으로 처리되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
