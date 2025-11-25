package com.example.umc9th_week5.domain.review.repository;

import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
//    StoreReview findById(Long id);

    @Query("select r from Review r where r.id = :userId order by r.id desc")
    Optional<Review> findReview(@Param("userId") Long userId);

    Page<Review> findAllByMember(Member member, PageRequest pageRequest);

}
