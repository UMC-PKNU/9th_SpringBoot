package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.Mission;
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

@RequiredArgsConstructor
@Repository
public class MissionQueryDslImpl implements MissionQueryDsl {

    private final EntityManager em;

    @Override
    public Page<Mission> searchMission(Long storeId, Pageable pageable) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QMission mission = QMission.mission;

        BooleanBuilder builder = new BooleanBuilder();

        if (storeId != null) {
            builder.and(mission.store.id.eq(storeId));
        }

        List<Mission> content = queryFactory
                .selectFrom(mission)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(mission.period.desc()) // 최신순 정렬 (필요시)
                .fetch();

        Long total = queryFactory
                .select(mission.count())
                .from(mission)
                .where(builder)
                .fetchOne();

        if (total == null)
            total = 0L;

        return new PageImpl<>(content, pageable, total);
    }
}
