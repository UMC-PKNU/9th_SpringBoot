// ReviewResponseDto.java

package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.StoreDto;
import com.example.umc9th.domain.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResponseDto {
    private final Long id;
    // ⭐ Member 엔티티 대신 MemberDto를 사용
    private final MemberDto member;
    private final String content;
    private final Long rating;
    private final LocalDateTime createdAt;
    private final StoreDto store;


    // Review 엔티티를 ReviewResponseDto로 변환하는 팩토리 메서드
    public static ReviewResponseDto from(Review review) {
        return ReviewResponseDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreated_at())
                // 엔티티에서 필요한 DTO를 만들어 주입
                .member(MemberDto.from(review.getMember()))
                .store(StoreDto.from(review.getStore()))
                .build();
    }
}