package com.example.umc_9th_spring.domain.inquiry.converter;

import com.example.umc_9th_spring.domain.inquiry.dto.res.InquiryResDTO;
import com.example.umc_9th_spring.domain.inquiry.entity.Inquiry;

public class InquiryConverter {
    public static InquiryResDTO.InquirySummary toInquirySummaryDTO(Inquiry inquiry) {
        return InquiryResDTO.InquirySummary.builder()
                .inquiryId(inquiry.getId())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }
}
