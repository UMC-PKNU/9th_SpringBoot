package com.example.umc9th_week5.domain.member.code;

import com.example.umc9th_week5.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberFoodCategoryErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERFOODCATEGORY400_2", "해당 음식 카테고리를 찾을 수 없습니다.");

    private HttpStatus status;
    private String code;
    private String message;

}
