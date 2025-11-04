package com.example.umc9th.domain.member.repository; // ⬅️ Repository의 패키지 경로

import com.example.umc9th.domain.member.entity.Member; // ⬅️ Member 엔티티 경로
import org.springframework.data.jpa.repository.JpaRepository; // ⬅️ Spring Data JPA 필수

import java.util.List; // ⬅️ findByNickname 반환 타입

public interface MemberRepository extends JpaRepository<Member, Long> {

    // JPA Query Method (메서드 이름으로 쿼리 생성)
    List<Member> findByNickname(String nickname);
}
