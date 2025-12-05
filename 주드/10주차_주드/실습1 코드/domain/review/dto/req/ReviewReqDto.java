package com.example.umc9th.domain.review.dto.req;

import lombok.Getter;

public class ReviewReqDto {
    @Getter
    public static class ReviewCreateDto {

        private String content; // 엔티티 필드명과 일치시킴
        private Integer rating; // Entity가 Long이므로 정수로 받음
    }
}
