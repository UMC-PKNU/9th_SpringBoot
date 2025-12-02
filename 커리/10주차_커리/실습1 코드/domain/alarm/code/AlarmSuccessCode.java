package com.example.umc_9th_spring.domain.alarm.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AlarmSuccessCode implements BaseSuccessCode {
    ALARM_SEND_SUCCESS(HttpStatus.OK, "ALARM200_1", "알림이 성공적으로 전송되었습니다."),
    ALARM_READ_SUCCESS(HttpStatus.OK, "ALARM200_2", "알림 읽기 상태가 업데이트되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
