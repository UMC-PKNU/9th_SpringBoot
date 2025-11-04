package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.MemberMissionDto; 
import com.example.umc9th.domain.memberMission.entity.MemberMission; 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query(
        "SELECT NEW com.example.umc9th.domain.mission.dto.MemberMissionDto(" +
        "   s.name, m.content, m.reward, mm.isComplete) " +
        "FROM MemberMission mm " +
        "JOIN mm.mission m " +  
        "JOIN m.store s " +     
        
        "WHERE mm.member.id = :memberId AND mm.isComplete = TRUE " +
        
        "ORDER BY mm.dueDate ASC" 
    )
    /**
     * 특정 회원의 완료된 미션 목록을 페이징 처리하여 조회합니다.
     * DTO Projection (NEW 키워드 사용) 및 자동 페이징 처리가 적용됩니다.
     * * @param memberId 조회할 회원의 ID
     * @param pageable 페이징 정보 (페이지 번호, 크기 등)
     * @return MemberMissionDto 타입의 Page 객체
     */
    Page<MemberMissionDto> findActiveMember(
        @Param("memberId") Long memberId,
        Pageable pageable
    );
}