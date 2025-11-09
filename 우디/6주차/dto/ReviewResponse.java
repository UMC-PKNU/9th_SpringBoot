package com.example.umc.domain.review.dto;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ReviewResponse {
    private Long reviewId;
    private String nickname;
    private Double rating;
    private String content;
    private String storeName;

    public ReviewResponse(Review review) {
        this.reviewId = review.getId();
        this.nickname = review.getMember().getNickname();
        this.rating = review.getRating();
        this.content = review.getContent();
        this.storeName = review.getStore().getName();
    }

    public static ReviewResponse of(Review review, Member member, Store store) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .nickname(member.getNickname())
                .rating(review.getRating())
                .content(review.getContent())
                .storeName(store.getName())
                .build();
    }
}
