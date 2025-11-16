package com.example.umc_9th_.domain.review.repository;

import com.example.umc_9th_.domain.review.dto.ReviewResponse.MyReviewDTO;
import com.example.umc_9th_.domain.review.entity.QReview;
import com.example.umc_9th_.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MyReviewDTO> findMyReviews(Long userId, String storeName, Integer ratingRange, Pageable pageable) {
        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(review.user.id.eq(userId));

        if (storeName != null && !storeName.isEmpty()) {
            builder.and(review.store.storeName.eq(storeName));
        }

        if (ratingRange != null) {
            double minRating = ratingRange;
            double maxRating = ratingRange + 0.9;
            builder.and(review.rating.between(minRating, maxRating));
        }

        List<MyReviewDTO> results = queryFactory
                .select(Projections.constructor(MyReviewDTO.class,
                        review.id,
                        review.store.storeName,
                        review.rating,
                        review.content,
                        review.createdAt))
                .from(review)
                .join(review.store, store)
                .where(builder)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(results, pageable, total != null ? total : 0);
    }
}
