package com.example.umc9th_week5.domain.review.service.query;

import com.example.umc9th_week5.domain.member.repository.MemberRepository;
import com.example.umc9th_week5.domain.review.converter.ReviewConverter;
import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_week5.domain.review.entity.QStoreReview;
import com.example.umc9th_week5.domain.review.entity.StoreReview;
import com.example.umc9th_week5.domain.review.exception.ReviewException;
import com.example.umc9th_week5.domain.review.repository.StoreReviewRepository;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import com.example.umc9th_week5.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th_week5.global.apiPayload.code.GeneralSuccessCode;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final StoreReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public ApiResponse<List<ReviewResDTO.ReviewInfo>> searchReview(Long userId, String storeName, Float rating){
        QStoreReview review = QStoreReview.storeReview;
        BooleanBuilder builder = new BooleanBuilder();

        //사용자 정보 확인
        if(userId == 0L || memberRepository.findMember(userId).isEmpty()){
            throw new ReviewException(GeneralErrorCode.NOT_FOUND);
        }

        builder.and(review.member.id.eq(userId));

        //가게 필터링
        if(StringUtils.hasText(storeName)){
            builder.and(review.store.name.eq(storeName));
        }

        //별점 필터링
        if(rating != null){
            builder.and(review.rating.between(rating, rating+1.0f-0.001f));
        }

        List<StoreReview> reviews = reviewRepository.searchReview(builder);

        List<ReviewResDTO.ReviewInfo> collect = reviews.stream()
                .map(r -> ReviewConverter.toReviewDTO(r))
                .collect(Collectors.toList());

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(code, collect);
    }
}
