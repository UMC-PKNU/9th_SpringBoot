package com.example.umc_9th_.domain.review.service;

import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.repository.ReviewRepository;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.mission.entity.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void writeReview(Long userId, Long storeId, Long missionId, String content, int rating) {
        // JPQL로 조회
        User user = reviewRepository.findUserByIdJPQL(userId);
        Store store = reviewRepository.findStoreByIdJPQL(storeId);
        Mission mission = reviewRepository.findMissionByIdJPQL(missionId);

        // Review 엔티티 생성
        Review review = Review.builder()
                .user(user)
                .store(store)
                .mission(mission)
                .content(content)
                .rating(rating)
                .createdAt(LocalDate.now())
                .build();

        // INSERT 실행 (JPA가 SQL 생성)
        reviewRepository.save(review);
    }
}
