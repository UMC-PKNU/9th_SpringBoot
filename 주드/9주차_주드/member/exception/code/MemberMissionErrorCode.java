package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001", "존재하지않는 미션입니다."),// 이거는 추후 미션 EXCEPTION에 넣어야할듯?
    MISSION_ALREADY_PROGRESSING(HttpStatus.BAD_REQUEST, "MEMBERMISSION4002", "이미 진행중인 미션입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}