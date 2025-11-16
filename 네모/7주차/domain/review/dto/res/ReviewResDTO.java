package com.example.umc9th_week5.domain.review.dto.res;

import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import com.example.umc9th_week5.domain.store.dto.res.StoreResDTO;
import lombok.Builder;
import lombok.Getter;

//@Getter
//@Builder
public class ReviewResDTO {
    /*
    private Long reviewId;
    private Float rating;
    private String content;
    private StoreResDto storeInfo;
    private MemberResDto memberInfo;

    public static ReviewResDto from(StoreReview review) {
        return ReviewResDto.builder()
                .reviewId(review.getId())
                .rating(review.getRating())
                .content(review.getContent())
                .storeInfo(StoreResDto.from(review.getStore()))
                .memberInfo(MemberResDto.from(review.getMember()))
                .build();
    }*/

    @Builder
    @Getter
    public static class ReviewInfo{
        private Long reviewId;
        private Float rating;
        private String content;
        private StoreResDTO.storeInfo storeInfo;
        private MemberResDTO.memberInfo memberInfo;
    }
}
