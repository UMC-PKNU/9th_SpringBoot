package com.example.umc9th_week5.domain.review.repository;

import com.example.umc9th_week5.domain.review.entity.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long>, ReviewQueryDsl {
//    StoreReview findById(Long id);

    @Query("select r from StoreReview r where r.id = :userId order by r.id desc")
    Optional<StoreReview> findReview(@Param("userId") Long userId);


}
