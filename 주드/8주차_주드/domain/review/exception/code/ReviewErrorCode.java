package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    // HTTP 400 Bad Request
    // 💡 필수 입력 값이 누락되었을 때 사용할 코드
    //REVIEW_MEMBER_ID_REQUIRED(HttpStatus.BAD_REQUEST, "REVIEW400_001", "리뷰 작성자 ID는 필수 입력 값입니다."),
    REVIEW_STORE_ID_REQUIRED(HttpStatus.BAD_REQUEST, "REVIEW400_002", "가게 이름은 필수 입력 값입니다."),
    REVIEW_RATING_REQUIRED(HttpStatus.BAD_REQUEST, "REVIEW400_003", "평점은 필수 입력 값입니다."),
    STORE_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"REVIEW4004", "가게 ID를 찾을 수 없습니다." ),
    // (예시: 리뷰를 찾을 수 없을 때)
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_001", "요청하신 리뷰를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;


}
