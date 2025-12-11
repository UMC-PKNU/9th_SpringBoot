package com.example.umc_9th_.domain.review.repository;

import com.example.umc_9th_.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    @Query("""
    SELECT r
    FROM Review r
    JOIN FETCH r.user u
    WHERE u.id = :userId
    ORDER BY r.createdAt DESC
""")
    List<Review> findReviewsByUserId(@Param("userId") Long userId);

}
