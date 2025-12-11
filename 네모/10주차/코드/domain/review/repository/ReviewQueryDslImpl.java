package com.example.umc9th_week5.domain.review.repository;

import com.example.umc9th_week5.domain.review.entity.QReview;
import com.example.umc9th_week5.domain.review.entity.Review;
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
    public List<Review> searchReview(Predicate predicate) {
        // jpa setting
        queryFactory = new JPAQueryFactory(em);

        // qclass
        QReview review = QReview.review;

        return queryFactory.selectFrom(review)
                .where(predicate)
                .fetch();
    }
}
