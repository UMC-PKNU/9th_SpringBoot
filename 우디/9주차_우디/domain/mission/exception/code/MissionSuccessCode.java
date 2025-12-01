package com.example.umc.domain.mission.exception.code;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_FOUND(HttpStatus.FOUND, "COMMON302_1", "해당 미션을 찾았습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
