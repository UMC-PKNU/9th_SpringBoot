package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    FOUND(HttpStatus.OK,
            "MEMBER2001",
            "성공적으로 사용자를 조회했습니다."),
    CREATED(HttpStatus.CREATED,
            "MEMBER2002",
            "성공적으로 회원가입이 완료되었습니다.")
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;

}
