package com.example.umc9th_week5.domain.mission.code;

import com.example.umc9th_week5.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    INVALID_INPUT(HttpStatus.BAD_REQUEST, "MEMBERMISSION400_1", "필수 값이 요청되지 않았습니다."),
    INVALID_STATE_TRANSITION(HttpStatus.NOT_FOUND, "MEMBERMISSION400_2", "사용자 미션 처리가 성공적으로 이루어지지 않았습니다."),
    ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBERMISSION400_3", "사용자는 이미 해당 미션을 수행 중이거나 완료했습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERMISSION400_4", "사용자의 해당 미션을 조회할 수 없습니다."),
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MEMBERMISSION400_5", "이미 완료된 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
