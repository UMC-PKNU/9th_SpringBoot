package com.example.umc_9th_.domain.review.service.query;

import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getMyReviews(Long userId, String storeName, Integer ratingRange, Pageable pageable) {
        // 엔티티 페이지를 그대로 반환 (변환은 컨트롤러/Converter에서)
        return reviewRepository.findMyReviews(userId, storeName, ratingRange, pageable);
    }
}