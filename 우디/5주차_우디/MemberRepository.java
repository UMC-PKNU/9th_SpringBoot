package com.example.umc.domain.member.repository;

import com.example.umc.domain.member.MemberDto;
import com.example.umc.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 미션2
    MemberDto findMyPageDtoById(Long id);

    // 미션4
    @Query("SELECT s.name, f.food, mi.moneyLowerLimit, mi.point, mi.period " +
            "FROM Member m " +
            "JOIN Store s ON m.position = s.position " +
            "JOIN s.food f " +
            "JOIN s.missions mi " +
            "WHERE m.id = :memberId " +
            "ORDER BY mi.id DESC")
    List<Object[]> findMissionsByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
