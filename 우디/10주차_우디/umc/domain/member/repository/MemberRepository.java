package com.example.umc.domain.member.repository;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 10주차
    Optional<Member> findByEmail(String email);

    // 5주차 워크북 내용
//    @Query("select m from Member m where m.name = :name and m.deleted_at is null")
//    List<Member> findActiveMember(@Param("name") String name);

    // 미션2 @Query를 사용하지 않는다면
    // 이 메서드를 사용해서 엔티티를 받아온 다음
    // 닉네임, 이메일, 번호, 포인트만 담는 DTO를 생성해야됨
//    Optional<Member> findMyPageInfoById(Long id);

    // 미션2
//    @Query("select m.nickname, m,email, m.phoneNumber, m.point from Member m where m.id = :id")
    @Query("select m from Member m where m.id = :id")
    Page<Member> findMyPageInfoById(@Param("id") Long id, Pageable pageable);


    // 미션4
//    @Query("SELECT m, mm, f, mi " +
//            "FROM Member m " +
//            "JOIN Store s ON m.position = s.position " +
//            "JOIN s.food f " +
//            "JOIN MemberMission mm ON mm.id = m.id " +
//            "JOIN Mission mi ON mm.id = mi.id " +
//            "WHERE m.id = :memberId " +
//            "AND mm.status = com.example.umc.domain.member.enums.MissionStatus.BEFORE"
//    )

    // 페이징이 안전하게 동작하기 위해서 모든 관계가 MemberMission 입장에서 N:1 이어야 한다.
    // 데이터 뻥튀기를 막기 위해서
    @Query(value = "SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.member m " +
            "JOIN FETCH mm.mission mi " +
            "JOIN FETCH mi.store s " +
            "JOIN FETCH s.food f " +
            "WHERE m.position = s.position " +
            "AND m.id = :memberId " +
            "AND mm.status = com.example.umc.domain.member.enums.MissionStatus.BEFORE",
            countQuery = "SELECT count(mm) FROM MemberMission mm " +
                    "JOIN mm.member m " +
                    "JOIN mm.mission mi " +
                    "JOIN mi.store s " +
                    "WHERE m.position = s.position " +
                    "AND m.id = :memberId " +
                    "AND mm.status = com.example.umc.domain.member.enums.MissionStatus.BEFORE"
    )
    Page<MemberMission> findMissionsByMemberId(@Param("memberId") Long memberId, Pageable pageable);


}
