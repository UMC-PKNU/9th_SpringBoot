package com.example.umc9th_week5.domain.mission.code;

import com.example.umc9th_week5.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    INVALID_INPUT(HttpStatus.BAD_REQUEST, "STORE400_1", "필수 값이 요청되지 않았습니다."),
    INVALID_STATE_TRANSITION(HttpStatus.NOT_FOUND, "STORE400_2", "사용자 미션 처리가 성공적으로 이루어지지 않았습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
