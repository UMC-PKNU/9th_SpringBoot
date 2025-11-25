package com.example.umc_9th_spring.domain.store.code;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    STORE_REGISTER_SUCCESS(HttpStatus.CREATED, "STORE201_1", "가게가 성공적으로 등록되었습니다."),
    STORE_UPDATE_SUCCESS(HttpStatus.OK, "STORE200_1", "가게 정보가 성공적으로 수정되었습니다."),
    STORE_DELETE_SUCCESS(HttpStatus.OK, "STORE200_2", "가게가 성공적으로 삭제되었습니다."),
    STORE_MISSION_LIST_FETCHED(HttpStatus.OK, "STORE200_3", "가게 미션 목록 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
