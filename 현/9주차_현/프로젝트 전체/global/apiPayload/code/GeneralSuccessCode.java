package com.example.umc_9th_.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {
    // 일반적인 성공 응답
    OK(HttpStatus.OK, "COMMON200", "요청이 성공적으로 처리되었습니다."),

    // 생성 성공 응답
    CREATED(HttpStatus.CREATED, "COMMON201", "자원이 성공적으로 생성되었습니다."),

    // 미션 관련 코드
    MISSION_START_SUCCESS(HttpStatus.OK, "MISSION2001", "미션 도전이 성공적으로 시작되었습니다."),
    MISSION_LOOKUP_SUCCESS(HttpStatus.OK, "MISSION2002", "미션 목록 조회가 성공적으로 완료되었습니다."),

    // 회원/위치 관련 코드

    // 리뷰 관련 코드
    REVIEW_REGISTER_SUCCESS(HttpStatus.CREATED, "REVIEW2011", "리뷰 등록이 성공적으로 완료되었습니다."),
    REVIEW_LOOKUP_SUCCESS(HttpStatus.OK, "REVIEW2001", "리뷰 목록 조회가 성공적으로 완료되었습니다."),
    MISSION_SUCCESS_REQUEST_SUCCESS(HttpStatus.OK, "MISSION2002", "미션 성공 요청이 성공적으로 처리되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
