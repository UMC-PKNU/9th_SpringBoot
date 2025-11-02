package com.example.umc9thweek5.domain.review.repository;

import com.example.umc9thweek5.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface reviewRepository extends JpaRepository<Review, Long> {

    // 특정 가게의 작성된 리뷰 목록을 최신순으로 조회하는 쿼리 + 사용자 정보도 가져왔음.
    @Query("""
        SELECT r
        FROM Review r
        JOIN FETCH r.user u
        JOIN FETCH r.store s
        WHERE s.id = :storeId
        ORDER BY r.createdAt DESC
    """)
    List<Review> findReviewsByStoreId(@Param("storeId") Long storeId);

}
