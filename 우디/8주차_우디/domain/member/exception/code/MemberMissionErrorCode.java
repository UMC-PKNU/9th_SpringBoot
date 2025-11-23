package com.example.umc.domain.member.exception.code;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    MISSION_ALREADY_CHALLENGING(HttpStatus.NOT_FOUND, "COMMON404_1", "해당 미션은 이미 진행중입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
