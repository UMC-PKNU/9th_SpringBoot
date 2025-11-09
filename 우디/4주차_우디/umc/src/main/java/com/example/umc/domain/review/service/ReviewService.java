package com.example.umc.domain.review.service;

import com.example.umc.domain.review.dto.ReviewResponse;
import com.example.umc.domain.review.entity.QReview;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.example.umc.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void updateReview(Long reviewId, Double newRating, String newContent) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        Review review = optionalReview.orElseThrow(() ->
                new EntityNotFoundException("Review not found with id: " + reviewId));

        review.updateReview(newRating, newContent);

        // 3. save() 호출 (선택 사항)
        // storeReviewRepository.save(review);
        // -> @Transactional 안에서는 save()를 생략해도
        //    JPA가 변경을 감지(Dirty Checking)해서 UPDATE 쿼리를 날려줍니다.
        //    updated_at도 @LastModifiedDate에 의해 자동으로 갱신됩니다.
//        reviewRepository.save(review);
    }

    public List<ReviewResponse> getReviews(Long memberId, String storeName, Double rating) {
        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        // 클라이언트로부터 가게이름과 별점을 반드시 받아오는게 아니기 때문에 조건문 사용
        if (storeName != null && !storeName.isEmpty()) {
            builder.and(store.name.eq(storeName));
        }

        if (rating != null) {
            builder.and(review.rating.eq(rating));
        }

        List<Review> reviews = reviewRepository.searchReview(builder);

        return reviews.stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }
}
