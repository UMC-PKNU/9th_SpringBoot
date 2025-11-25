package com.example.umc_9th_spring.domain.mission.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    MISSION_LIST_FETCHED(HttpStatus.OK, "MISSION200_1", "미션 목록 조회 성공"),
    MISSION_COMPLETED(HttpStatus.OK, "MISSION200_2", "미션 완료 성공"),
    MISSION_CREATED(HttpStatus.CREATED, "MISSION201_1", "미션 생성 성공"),
    MISSION_RECEIVED(HttpStatus.CREATED, "MISSION201_2", "미션 받기 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
