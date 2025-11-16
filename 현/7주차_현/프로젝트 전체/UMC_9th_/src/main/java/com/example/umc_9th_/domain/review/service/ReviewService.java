package com.example.umc_9th_.domain.review.service;

import com.example.umc_9th_.domain.review.dto.ReviewRequest;
import com.example.umc_9th_.domain.review.dto.ReviewResponse;
import com.example.umc_9th_.domain.review.repository.ReviewRepository;
import com.example.umc_9th_.domain.review.dto.ReviewResponse.MyReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 리뷰 등록 처리
    @Transactional
    public ReviewResponse.ReviewIdDTO writeReview(ReviewRequest.WriteReviewDTO request, Long userId) {
        // 1. User, Mission, Store 유효성 검사 및 엔티티 조회
        // 2. Review 엔티티 및 ReviewImg 엔티티 생성 및 저장
        // 3. 포인트 지급

        // 임시 응답 데이터 반환
        return ReviewResponse.ReviewIdDTO.builder()
                .reviewId(125L)
                .build();
    }

    // 마이페이지 리뷰 목록 조회 및 페이징
    public ReviewResponse.MyReviewPageDTO getMyReviews(Long userId, String storeName, Integer ratingRange, Pageable pageable) {

        // 1. ReviewRepository를 사용하여 필터링 및 페이징된 Review 목록 조회
        Page<MyReviewDTO> reviewPage = reviewRepository.findMyReviews(userId, storeName, ratingRange, pageable);

        // 2. 조회된 Page<MyReviewDTO>를 최종 응답 DTO로 변환
        return ReviewResponse.MyReviewPageDTO.builder()
                .currentPage(pageable.getPageNumber() + 1)
                .totalPages(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .pageSize(reviewPage.getSize())
                .reviewList(reviewPage.getContent())
                .build();
    }
}