package com.example.umc9th_week5.domain.member.repository;

import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.mission.entity.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    Member findById(Long id);

    @Query("select m from Member m where m.id = :userId")
    Member findMember(@Param("userId") Long userId);
}

