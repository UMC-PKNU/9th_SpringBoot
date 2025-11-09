package com.example.umc9th_week5.domain.review.repository;

import com.example.umc9th_week5.domain.review.entity.StoreReview;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {
    List<StoreReview> searchReview(Predicate predicate);
}