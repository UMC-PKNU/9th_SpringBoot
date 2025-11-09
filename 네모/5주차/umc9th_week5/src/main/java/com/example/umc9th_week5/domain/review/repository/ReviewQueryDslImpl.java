package com.example.umc9th_week5.domain.review.repository;

import com.example.umc9th_week5.domain.review.entity.QStoreReview;
import com.example.umc9th_week5.domain.review.entity.StoreReview;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final EntityManager em;

    @Override
    public List<StoreReview> searchReview(Predicate predicate) {
        // jpa setting
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // qclass
        QStoreReview review = QStoreReview.storeReview;

        return queryFactory.selectFrom(review)
                .where(predicate)
                .fetch();
    }
}
