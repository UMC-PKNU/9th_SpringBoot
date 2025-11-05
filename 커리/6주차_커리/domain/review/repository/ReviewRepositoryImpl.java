package com.example.umc_9th_spring.domain.review.repository;

import com.example.umc_9th_spring.domain.review.entity.QReview;
import com.example.umc_9th_spring.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findUserReviews(Long userId, Long storeId, String rating) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 로그인 구현 전이라, 파라미터 기반 필터
        if (userId != null) builder.and(review.user.id.eq(userId));
        if (storeId != null) builder.and(review.store.id.eq(storeId));

        // 별점 (5,4,3점대. . 필터 추가)
        if (rating != null) {
            switch (rating) {
                case "5" -> builder.and(review.rating.eq(5));
                case "4" -> builder.and(review.rating.goe(4).and(review.rating.lt(5)));
                case "3" -> builder.and(review.rating.goe(3).and(review.rating.lt(4)));
                case "2" -> builder.and(review.rating.goe(2).and(review.rating.lt(3)));
                case "1" -> builder.and(review.rating.goe(1).and(review.rating.lt(2)));
            }
        }

        return queryFactory.selectFrom(review)
                .where(builder)
                .orderBy(review.createdAt.desc())
                .fetch();
    }

}
