package com.example.umc_9th_spring.domain.inquiry.repository;

import com.example.umc_9th_spring.domain.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    // 특정 유저가 작성한 문의 내역 조회
    // -> SELECT i FROM Inquiry i WHERE i.user.id = :userId ORDER BY i.createdAt DESC
    List<Inquiry> findAllByIdOrderByCreatedAtDesc(Long userId);
}
