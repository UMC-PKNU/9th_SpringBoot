package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    Page<Review> searchReview(Long memberId, Store store, Pageable pageable);
    // findBy(id) 이용해서 미션1 해결
}
