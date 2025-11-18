package com.example.umc_9th_spring.domain.review.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    REVIEW_CREATE_SUCCESS(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 성공적으로 등록되었습니다."),
    REVIEW_UPDATE_SUCCESS(HttpStatus.OK, "REVIEW200_1", "리뷰가 성공적으로 수정되었습니다."),
    REVIEW_DELETE_SUCCESS(HttpStatus.OK, "REVIEW200_2", "리뷰가 성공적으로 삭제되었습니다."),
    REVIEW_LIST_ME_FETCHED(HttpStatus.OK, "REVIEW200_3", "특정 사용자에 의해 작성된 리뷰가 성공적으로 조회되었습니다."),
    REVIEW_LIST_STORE_FETCHED(HttpStatus.OK, "REVIEW200_4", "특정 가게에 작성된 리뷰가 성공적으로 조회되었습니다."),
    REVIEW_FITERED_LIST_FETCHED(HttpStatus.OK, "REVIEWD200_5", "필터링된 리뷰가 성공적으로 조회되었습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
