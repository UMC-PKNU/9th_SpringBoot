package com.example.umc.domain.member.exception.code;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾지 못했습니다."),


    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH401_1", "로그인이 필요합니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404_1", "회원를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
