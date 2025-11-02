package com.example.umc_9th_.domain.review.repository;

import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // JPQL로 User 조회
    @Query("SELECT u FROM User u WHERE u.id = :userId")
    User findUserByIdJPQL(@Param("userId") Long userId);

    // JPQL로 Store 조회
    @Query("SELECT s FROM Store s WHERE s.id = :storeId")
    Store findStoreByIdJPQL(@Param("storeId") Long storeId);

    // JPQL로 Mission 조회
    @Query("SELECT m FROM Mission m WHERE m.id = :missionId")
    Mission findMissionByIdJPQL(@Param("missionId") Long missionId);

    // 내(userId) 아이디로 작성된 리뷰 조회
    @Query("""
        SELECT r
        FROM Review r
        JOIN FETCH r.user u
        WHERE u.id = :userId
        ORDER BY r.createdAt DESC
    """)
    List<Review> findReviewsByUserId(@Param("userId") Long userId);
}
