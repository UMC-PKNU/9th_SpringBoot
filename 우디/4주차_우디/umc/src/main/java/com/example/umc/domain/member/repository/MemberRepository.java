package com.example.umc.domain.member.repository;

import com.example.umc.domain.member.dto.MemberDto;
import com.example.umc.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 미션2

    // 미션4
    @Query("SELECT m, mm, f, mi " +
            "FROM Member m " +
            "JOIN Store s ON m.position = s.position " +
            "JOIN s.food f " +
            "JOIN MemberMission mm ON mm.id = m.id " +
            "JOIN Mission mi ON mm.id = mi.id " +
            "WHERE m.id = :memberId " +
            "AND mm.status = com.example.umc.domain.member.enums.MissionStatus.BEFORE"
    )
    Page<Object[]> findMissionsByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
