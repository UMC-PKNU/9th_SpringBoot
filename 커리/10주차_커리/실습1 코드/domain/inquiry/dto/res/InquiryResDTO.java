package com.example.umc_9th_spring.domain.inquiry.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class InquiryResDTO {

    @Getter
    @Builder
    public static class InquirySummary {
        private Long inquiryId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
    }
}
