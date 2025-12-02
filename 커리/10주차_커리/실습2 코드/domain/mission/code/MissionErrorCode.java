package com.example.umc_9th_spring.domain.mission.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_RECEIVED(HttpStatus.BAD_REQUEST, "MISSION400_1", "유저가 해당 미션을 받지 않았습니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION400_2", "이미 완료된 미션입니다."),
    MISSION_ALREADY_RECEIVED(HttpStatus.BAD_REQUEST, "MISSION400_3", "이미 받은 미션입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "존재하지 않는 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
