package com.example.umc9th_week5.domain.inquiry.dto.res;

import com.example.umc9th_week5.domain.inquiry.enums.InquiryCategory;
import com.example.umc9th_week5.domain.inquiry.enums.InquiryStatus;
import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import lombok.Builder;
import lombok.Getter;

public class InquiryResDTO {
    @Getter
    @Builder
    public static class InquiryInfo{
        private Long InquiryId;
        private MemberResDTO.MemberInfo member;
        private String title;
        private String content;
        private InquiryCategory inquiryCategory;
        private InquiryStatus inquiryStatus;
    }
}
