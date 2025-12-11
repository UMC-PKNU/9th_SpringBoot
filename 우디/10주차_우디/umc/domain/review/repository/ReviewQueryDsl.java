package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.store.entity.Store;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryDsl {

//    Page<Review> searchReview(Predicate predicate);
    Page<Review> searchReview(Long memberId, Store store, Pageable pageable);
}
