package com.example.umc_9th_.domain.mission.exception.code;

import com.example.umc_9th_.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_START(HttpStatus.CREATED, "MISSION201_1", "미션 도전이 시작되었습니다."),
    MISSION_SUCCESS(HttpStatus.OK, "MISSION200_1", "미션 성공 인증이 완료되었습니다."),
    MISSION_LIST_FETCH(HttpStatus.OK, "MISSION200_2", "미션 목록 조회가 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}