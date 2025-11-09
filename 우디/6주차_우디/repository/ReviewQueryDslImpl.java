package com.example.umc.domain.review.repository;

import com.example.umc.domain.member.entity.QMember;
import com.example.umc.domain.review.entity.QReview;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

//    private final ReviewRepository reviewRepository;
    private final EntityManager em;

    @Override
    public List<Review> searchReview(Predicate predicate) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;
        QStore store = QStore.store;
        QMember member = QMember.member;

        return queryFactory.
                selectFrom(review)
                .join(review.store, store)
                .join(review.member, member)
                .where(predicate)
                .orderBy(review.rating.desc())
                .fetch();
    }
}
