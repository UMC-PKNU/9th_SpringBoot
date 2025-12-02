package com.example.umc_9th_spring.domain.review.repository;

import com.example.umc_9th_spring.domain.review.entity.QReview;
import com.example.umc_9th_spring.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    //여기서 조건 조립은 서비스 층에게 맡기고 실제 "쿼리 실행" 부분만 여기서 구현


    @Override
    public List<Review> findUserReviewsFilter(Predicate predicate) {
        QReview review = QReview.review;

        return queryFactory.selectFrom(review)
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }



}
