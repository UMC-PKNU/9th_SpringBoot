package com.example.umc_9th_spring.domain.review.service.query;

import com.example.umc_9th_spring.domain.review.converter.ReviewConverter;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.entity.QReview;
import com.example.umc_9th_spring.domain.review.entity.Review;
import com.example.umc_9th_spring.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewResDTO.ReviewInfo> getReviews(Long userId, Long storeId, String rating){
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (userId != null) {
            builder.and(review.user.id.eq(userId));
        }

        if (storeId != null) {
            builder.and(review.store.id.eq(storeId));
        }

        if (rating != null) {
            switch (rating) {
                case "5" -> builder.and(review.rating.eq(5));
                case "4" -> builder.and(review.rating.between(4, 4.99));
                case "3" -> builder.and(review.rating.between(3, 3.99));
                case "2" -> builder.and(review.rating.between(2, 2.99));
                case "1" -> builder.and(review.rating.between(1, 1.99));
            }
        }

        // BooleanBuilder는 Predicate 인터페이스 구현체이므로 그대로 전달 가능
        List<Review> reviews = reviewRepository.findUserReviews(builder);

        return reviews.stream()
                .map(ReviewConverter::toReviewInfoDTO)
                .toList();

    }

}
