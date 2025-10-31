package com.example.umc9thweek5.domain.user.repository;

import com.example.umc9thweek5.domain.review.entity.Review;
import com.example.umc9thweek5.domain.inquiry.entity.Inquiry;
import com.example.umc9thweek5.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface userRepository extends JpaRepository<User, Long> {

    //로그인 된 사용자의 마이페이지 초기 화면에서 닉네임과 같은 정보를 표현하기 위한 쿼리
    Optional<User> findById(Long id);

    @Query("""
        SELECT r
        FROM Review r
        JOIN FETCH r.user u
        WHERE u.id = :userId
        ORDER BY r.createdAt DESC
    """)
    List<Review> findUserReviews(@Param("userId") Long userId);

    // 특정 유저가 작성한 문의 내역 조회
    // -> SELECT i FROM Inquiry i WHERE i.user.id = :userId ORDER BY i.createdAt DESC
    List<Inquiry> findAllByUserIdOrderByCreatedAtDesc(Long userId);
}
