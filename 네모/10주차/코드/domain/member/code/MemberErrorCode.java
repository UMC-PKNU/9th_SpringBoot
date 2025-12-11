package com.example.umc9th_week5.domain.member.code;

import com.example.umc9th_week5.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "MEMBER400_1", "필수 값이 요청되지 않았습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER400_2", "해당 사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER400_3", "비밀번호가 일치하지 않습니다.");
    
    private HttpStatus status;
    private String code;
    private String message;
}
