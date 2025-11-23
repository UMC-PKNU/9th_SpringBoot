package com.example.umc.domain.member.exception.code;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK, "MEMBER200_1", "성공적으로 사용자를 조회했습니다."),


    GET_MISSIONS_SUCCESS(HttpStatus.OK, "COMMON200_1", "미션 목록 조회 성공"),
    GET_MY_PAGE_SUCCESS(HttpStatus.OK, "COMMON200_2", "마이페이지 접근 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
