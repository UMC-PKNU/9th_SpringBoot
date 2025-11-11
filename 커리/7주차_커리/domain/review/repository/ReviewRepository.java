package com.example.umc_9th_spring.domain.review.repository;

import com.example.umc_9th_spring.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

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
