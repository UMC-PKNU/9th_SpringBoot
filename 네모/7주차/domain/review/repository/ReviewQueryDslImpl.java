package com.example.umc9th_week5.domain.review.repository;

import com.example.umc9th_week5.domain.review.entity.QStoreReview;
import com.example.umc9th_week5.domain.review.entity.StoreReview;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final EntityManager em;
    JPAQueryFactory queryFactory;

    @Override
    public List<StoreReview> searchReview(Predicate predicate) {
        // jpa setting
        queryFactory = new JPAQueryFactory(em);

        // qclass
        QStoreReview review = QStoreReview.storeReview;

        return queryFactory.selectFrom(review)
                .where(predicate)
                .fetch();
    }
}
