package com.example.umc_9th_.domain.review.service;

import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.dto.MyReviewResponse;
import com.example.umc_9th_.domain.review.repository.ReviewRepository;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.repository.UserRepository;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.store.repository.StoreRepository;
import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    /**
     * 리뷰 등록
     */
    public void writeReview(Long userId, Long storeId, Long missionId, String content, int rating) {
        // 각 엔티티 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        // Review 엔티티 생성 및 저장
        Review review = Review.builder()
                .user(user)
                .store(store)
                .mission(mission)
                .content(content)
                .rating((double) rating)
                .build();

        reviewRepository.save(review);
    }

    /**
     * 내가 작성한 리뷰 조회 (QueryDSL)
     */
    public Page<MyReviewResponse> getMyReviews(Long userId, String storeName, Integer ratingRange, Pageable pageable) {
        return reviewRepository.findMyReviews(userId, storeName, ratingRange, pageable);
    }
}
