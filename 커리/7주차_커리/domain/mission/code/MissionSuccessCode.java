package com.example.umc_9th_spring.domain.mission.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    MISSION_COMPLETE_SUCCESS(HttpStatus.OK, "MISSION200_1", "미션이 성공적으로 완료되었습니다."),
    MISSION_LIST_FETCHED(HttpStatus.OK, "MISSION200_2", "지역별 미션이 성공적으로 조회되었습니다.."),
    MISSION_CREATE_SUCCESS(HttpStatus.CREATED, "MISSION201_1", "미션이 성공적으로 등록되었습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
