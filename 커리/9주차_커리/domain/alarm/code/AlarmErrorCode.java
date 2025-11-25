package com.example.umc_9th_spring.domain.alarm.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AlarmErrorCode implements BaseErrorCode {
    ALARM_NOT_FOUND(HttpStatus.NOT_FOUND, "ALARM404_1", "해당 알림을 찾을 수 없습니다."),
    ALARM_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "ALARM500_1", "알림 전송에 실패했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
