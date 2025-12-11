package com.example.umc9th_week5.domain.member.repository;

import com.example.umc9th_week5.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    Member findById(Long id);

    Optional<Member> findById(Long memberId);
    Optional<Member> findByEmail(String email);
}

