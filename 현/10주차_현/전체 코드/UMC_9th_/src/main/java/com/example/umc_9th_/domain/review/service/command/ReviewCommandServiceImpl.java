package com.example.umc_9th_.domain.review.service.command;

import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.mission.repository.MissionRepository;
import com.example.umc_9th_.domain.review.dto.ReviewRequest;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.entity.ReviewImg;
import com.example.umc_9th_.domain.review.repository.ReviewRepository;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.store.repository.StoreRepository;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public Review writeReview(ReviewRequest.WriteReviewDTO request, Long userId) {
        // 1. 검증 및 엔티티 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        // 2. 리뷰 생성
        Review review = Review.builder()
                .user(user)
                .store(store)
                .mission(mission)
                .rating(request.rating())
                .content(request.content())
                .build();

        // 3. 이미지 저장 (있는 경우만)
        if (request.imageUrls() != null && !request.imageUrls().isEmpty()) {
            request.imageUrls().forEach(url -> {
                ReviewImg reviewImg = ReviewImg.builder()
                        .review(review)
                        .imageUrl(url)
                        .build();
                review.getReviewImgs().add(reviewImg); // 연관관계 편의 메서드처럼 동작
            });
        }

        return reviewRepository.save(review);
    }
}