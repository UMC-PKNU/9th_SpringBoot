package com.example.umc.domain.member.exception.code;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    MISSION_CHALLENGE_CREATED(HttpStatus.OK, "COMMON200_1", "미션을 성공적으로 추가했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
