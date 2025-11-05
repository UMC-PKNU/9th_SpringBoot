package com.example.umc_9th_spring.domain.review.repository;

import com.example.umc_9th_spring.domain.review.entity.Review;

import java.util.List;

//커스텀 query를 만들기 위한 커스텀 인터페이스
public interface ReviewRepositoryCustom {
    List<Review> findUserReviews(Long userId, Long storeId, String rating);
}
