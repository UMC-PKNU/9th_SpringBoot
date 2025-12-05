package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.StoreDto;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static ReviewResDto.ReviewResListDTO toReviewResListDTO(Page<Review> reviewList) {

        // 1. Review 엔티티 리스트를 DTO 리스트로 변환 (Stream 사용)
        List<ReviewResDto.ReviewResPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewResPreViewDTO)
                .collect(Collectors.toList());

        // 2. 전체 응답 DTO 빌드 (Builder 패턴 사용)
        return ReviewResDto.ReviewResListDTO.builder()
                .reviewList(reviewPreViewDTOList)
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .build();
    }
    public static ReviewResDto.ReviewResDefaultDto toReviewResDefaultDTO(
            Review review
    ){
        return ReviewResDto.ReviewResDefaultDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreated_at())
                .member(MemberDto.from(review.getMember()))
                .store(StoreDto.from(review.getStore()))
                .build();
    }
    public static ReviewResDto.ReviewResPreViewDTO toReviewResPreViewDTO(Review review) {
        return ReviewResDto.ReviewResPreViewDTO.builder()
                .nickname(review.getMember().getNickname())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreated_at().toLocalDate())
                .build();
    }

    public static ReviewResDto.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDto.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreated_at())
                .build();
    }

    public static Review toReview(ReviewReqDto.ReviewCreateDto request, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .content(request.getContent())
                .rating(request.getRating().longValue())
                .build();
    }
}
