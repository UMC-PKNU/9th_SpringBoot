package com.example.umc9th.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    List<Review> searchReview(
            Predicate predicate

    );

    List<Review> findReviewsByStoreAndRating(
            Predicate predicate
    );
}
