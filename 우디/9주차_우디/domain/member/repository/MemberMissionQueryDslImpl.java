package com.example.umc.domain.member.repository;

import com.example.umc.domain.member.entity.mapping.MemberMission;
import com.example.umc.domain.member.entity.mapping.QMemberMission;
import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.mission.entity.QMission;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionQueryDslImpl implements MemberMissionQueryDsl {

    private final EntityManager em;

    // 9주차 미션3
    @Override
    public Page<MemberMission> findMemberMissions(Long memberId, MissionStatus status, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q클래스 선언
        QMemberMission memberMission = QMemberMission.memberMission;
        QMission mission = QMission.mission;

        BooleanBuilder builder = new BooleanBuilder();

        // 1. 특정 회원의 것만
        builder.and(memberMission.member.id.eq(memberId));

        // 2. 상태 조건 (진행중, 완료 등)
        if (status != null) {
            builder.and(memberMission.status.eq(status));
        }

        // 3. 데이터 조회 (MemberMission을 조회하되, Mission 정보도 같이 가져옴 - Fetch Join)
        List<MemberMission> content = queryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission, mission).fetchJoin() // 미션 정보 로딩 (N+1 방지)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(memberMission.createdAt.desc()) // 도전한 최신순
                .fetch();

        // 4. 전체 개수 조회
        Long total = queryFactory
                .select(memberMission.count())
                .from(memberMission)
                .where(builder)
                .fetchOne();

        if (total == null)
            total = 0L;

        return new PageImpl<>(content, pageable, total);
    }
}
