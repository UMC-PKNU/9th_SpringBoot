package com.example.umc9th.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(
            String query,
            String type
    ) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("location")) {
            builder.and(review.store.located_at.contains(query));
        }
        if (type.equals("rating")) {
            builder.and(review.rating.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")) {

            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(review.store.located_at.contains(firstQuery));
            builder.and(review.rating.goe(Float.parseFloat(secondQuery)));

        }

        List<Review> reviewList = reviewRepository.searchReview((builder));

        return reviewList;
    }

    public List<Review> findReviewsByStoreAndRating(
            Long mem_id,
            String store_name,
            Long rating
    ) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (mem_id == null) {
            // 비즈니스 로직에 따라 NullPointerException 또는 IllegalArgumentException을 던질 수 있습니다.
            throw new IllegalArgumentException("회원 ID(mem_id)는 필수 조건입니다.");
        }

        builder.and(review.member.id.eq(mem_id));

        if (store_name != null){
            builder.and(review.store.name.eq(store_name));
        }
        if (rating != null) {
            // rating 값이 있으면 정확히 일치하는 리뷰를 필터링합니다.
            // null이면 해당 멤버가 쓴 리뷰 전체 출력
            builder.and(review.rating.eq(rating));
        }

        List<Review> reviewList = reviewRepository.findReviewsByStoreAndRating((builder));

        return reviewList;

    }
}
