package com.example.umc9th.domain.mission.repository; 

import com.example.umc9th.domain.mission.dto.MissionSummaryDto; 
import com.example.umc9th.domain.memberMission.entity.MemberMission; 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime; 


public interface MissionRepository extends JpaRepository<MemberMission, Long> {

    @Query(
        "SELECT NEW com.example.umc9th.domain.mission.dto.MissionSummaryDto(" +
        "   s.locatedAt, s.storeType, s.name, mm.dueDate, m.content, m.reward) " +
        
        "FROM MemberMission mm " +
        "JOIN mm.mission m " +  // mm 엔티티의 mission 필드를 이용해 Mission과 조인
        "JOIN m.store s " +     // m 엔티티의 store 필드를 이용해 Store와 조인
        
        "WHERE mm.member.id = :memberId " +
        "  AND mm.isComplete = FALSE " + // 미완료 미션만 조회
        "  AND s.locatedAt LIKE :regionQuery " + // LIKE 연산자를 사용하여 지역 검색
        
        "ORDER BY mm.dueDate ASC"
    )
    /**
     * 특정 회원의 미완료 미션 중 특정 지역에 위치한 미션 목록을 페이징 처리하여 조회합니다.
     * @param memberId 조회할 회원의 ID
     * @param regionQuery LIKE 검색에 사용될 지역 문자열 (예: "서울%").
     * @param pageable 페이징 정보 (페이지 번호, 크기 등)
     * @return MissionSummaryDto 타입의 Page 객체
     */
    Page<MissionSummaryDto> findIncompleteMissionsByMemberAndRegion(
        @Param("memberId") Long memberId,
        @Param("regionQuery") String regionQuery,
        Pageable pageable
    );
}