package com.example.umc.domain.inquiry.exception.code;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InquirySuccessCode implements BaseSuccessCode {

    GET_INQUIRY_SUCCESS(HttpStatus.OK, "INQUIRY200_1", "문의 상세 조회에 성공하였습니다."),
    GET_INQUIRY_LIST_SUCCESS(HttpStatus.OK, "INQUIRY200_2", "문의 목록 조회에 성공하였습니다."),

    UPDATE_INQUIRY_SUCCESS(HttpStatus.OK, "INQUIRY200_3", "문의 수정에 성공하였습니다."),
    DELETE_INQUIRY_SUCCESS(HttpStatus.OK, "INQUIRY200_4", "문의 삭제에 성공하였습니다."),

    ANSWER_INQUIRY_SUCCESS(HttpStatus.OK, "INQUIRY200_5", "문의 답변이 완료하였습니다."),

    CREATE_INQUIRY_SUCCESS(HttpStatus.CREATED, "INQUIRY201_1", "문의 등록에 성공하였습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
