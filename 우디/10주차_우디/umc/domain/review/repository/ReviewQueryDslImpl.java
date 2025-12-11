package com.example.umc.domain.review.repository;

import com.example.umc.domain.member.entity.QMember;
import com.example.umc.domain.review.entity.QReview;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.store.entity.QStore;
import com.example.umc.domain.store.entity.Store;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
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
public class ReviewQueryDslImpl implements ReviewQueryDsl {

//    private final ReviewRepository reviewRepository;
    private final EntityManager em;

//     @Override
//    public Page<Review> searchReview(Predicate predicate) {
//
//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//
//        QReview review = QReview.review;
//        QStore store = QStore.store;
//        QMember member = QMember.member;
//
//        return queryFactory.
//                selectFrom(review)
//                // N+1 문제를 해결하기 위해서 페치조인 사용
//                .join(review.store, store).fetchJoin()
//                .join(review.member, member).fetchJoin()
//                .where(predicate)
//                .orderBy(review.rating.desc())
//                .fetch();
//    }

    @Override
    public Page<Review> searchReview(Long memberId, Store store, Pageable pageable) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;

        // 1. 검색 조건 만들기 (BooleanBuilder)
        BooleanBuilder builder = new BooleanBuilder();

        // memberId가 있으면 조건 추가
        if (memberId != null) {
            builder.and(review.member.id.eq(memberId));
        }

        // store 객체가 있으면 조건 추가
        if (store != null) {
            builder.and(review.store.eq(store));
        }

        // 2. 데이터 조회 (페이징 적용)
        List<Review> content = queryFactory
                .selectFrom(review)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createdAt.desc()) // 최신순 정렬 (필요시)
                .fetch();

        // 3. 전체 개수 조회 (Count Query)
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();

        if (total == null) total = 0L;

        // 4. Page 객체 반환
        return new PageImpl<>(content, pageable, total);
    }
}
