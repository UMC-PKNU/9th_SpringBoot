package com.example.umc_9th_spring.domain.review.service.query;

import com.example.umc_9th_spring.domain.review.converter.ReviewConverter;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.entity.QReview;
import com.example.umc_9th_spring.domain.review.entity.Review;
import com.example.umc_9th_spring.domain.review.repository.ReviewRepository;
import com.example.umc_9th_spring.domain.store.code.StoreErrorCode;
import com.example.umc_9th_spring.domain.store.exception.StoreException;
import com.example.umc_9th_spring.domain.store.repository.StoreRepository;
import com.example.umc_9th_spring.domain.user.code.UserErrorCode;
import com.example.umc_9th_spring.domain.user.exception.UserException;
import com.example.umc_9th_spring.domain.user.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    /* 02-01 특정 유저가 작성한 리뷰 조회 API*/
    @Override
    public List<ReviewResDTO.ReviewInfo> getUserReviews(Long userId) {

        // 1. null 값 검사
        if(userId == null){
            throw new UserException(UserErrorCode.USER_NULL_EXCEPTION);
        }

        // 2. 존재하는 유저인지 검사
        if(!userRepository.existsById(userId)){
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }


        List<Review> reviews = reviewRepository.findUserReviews(userId);

        return reviews.stream()
                .map(ReviewConverter::toReviewInfoDTO)
                .toList();

    }

    /* 02-02 특정 가게 리뷰 조회 API*/
    @Override
    public List<ReviewResDTO.ReviewInfo> getStoreReviews(Long storeId) {

        // [1] null 검사
        if (storeId == null) {
            throw new StoreException(StoreErrorCode.STORE_NULL_EXCEPTION);
        }

        // [2] 실제 존재하는 store인지 검사
        if (!storeRepository.existsById(storeId)) {
            throw new StoreException(StoreErrorCode.STORE_NOT_FOUND);
        }
        List<Review> reviews = reviewRepository.findReviewsByStoreId(storeId);

        return reviews.stream()
                .map(ReviewConverter::toReviewInfoDTO)
                .toList();
    }

    /* 02-03 특정 가게 리뷰 별점순으로 필터링된 리뷰 목록 조회 API*/
    @Override
    public List<ReviewResDTO.ReviewInfo> getReviewsFilter(Long userId, Long storeId, String rating){
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
        List<Review> reviews = reviewRepository.findUserReviewsFilter(builder);

        return reviews.stream()
                .map(ReviewConverter::toReviewInfoDTO)
                .toList();

    }

}
