package com.example.umc.domain.review.service.query;

import com.example.umc.domain.review.converter.ReviewConverter;
import com.example.umc.domain.review.dto.res.ReviewResDto;
import com.example.umc.domain.review.entity.QReview;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
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

    @Override
    public List<ReviewResDto.ReviewInfo> getReviews(Long memberId, String storeName, Double rating) {
        QReview review = QReview.review;
//        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        if (storeName != null && !storeName.isEmpty()) {
            builder.and(review.store.name.eq(storeName));
        }

        if (rating != null) {
//            builder.and(review.rating.eq(rating));
            builder.and(review.rating.goe(rating))
                    .and(review.rating.lt(rating+1.0));
        }

        List<Review> reviews = reviewRepository.searchReview(builder);

        List<ReviewResDto.ReviewInfo> resultList = new ArrayList<>();
        for (Review r: reviews) {
            ReviewResDto.ReviewInfo dto = ReviewConverter.GetReviewInfoList(r, r.getMember(), r.getStore());
            resultList.add(dto);
        }

        return resultList;
    }
}
