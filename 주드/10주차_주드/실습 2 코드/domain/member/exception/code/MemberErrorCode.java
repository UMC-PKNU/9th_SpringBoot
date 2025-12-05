package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER400_001", "존재하지않는 멤버입니다."),
    NICKNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER400_002", "이미 존재하는 닉네임입니다."),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER400_003", "이미 존재하는 이메일입니다."),
    PHONE_NUMBER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER400_004", "이미 존재하는 전화번호입니다."),
    INVALID(HttpStatus.BAD_REQUEST, "MEMBER400_005","비밀번호가 올바르지 않습니다.")
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
