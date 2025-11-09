package com.example.umc9th_week5.domain.review.service;

import com.example.umc9th_week5.domain.review.dto.res.ReviewResDto;
import com.example.umc9th_week5.domain.review.entity.QStoreReview;
import com.example.umc9th_week5.domain.review.entity.StoreReview;
import com.example.umc9th_week5.domain.review.repository.StoreReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final StoreReviewRepository reviewRepository;

    public List<ReviewResDto> searchReview(String storeName, Float rate){
        QStoreReview review = QStoreReview.storeReview;
        BooleanBuilder builder = new BooleanBuilder();

        //가게 필터링
        if(StringUtils.hasText(storeName)){
            builder.and(review.store.name.containsIgnoreCase(storeName));
        }

        //별점 필터링
        if(rate != null){
            builder.and(review.rating.between(rate, rate+1.0f-0.001f));
        }

        List<StoreReview> reviews = reviewRepository.searchReview(builder);

        return reviews.stream()
                .map(ReviewResDto::from)    //.map(review -> ReviewResDto.from(review))
                .collect(Collectors.toList());

    }
}
