package com.example.umc9th_week5.domain.review.service.command;

import com.example.umc9th_week5.domain.member.code.MemberErrorCode;
import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.member.repository.MemberRepository;
import com.example.umc9th_week5.domain.review.converter.ReviewConverter;
import com.example.umc9th_week5.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_week5.domain.review.entity.Review;
import com.example.umc9th_week5.domain.review.repository.ReviewRepository;
import com.example.umc9th_week5.domain.store.code.StoreErrorCode;
import com.example.umc9th_week5.domain.store.entity.Store;
import com.example.umc9th_week5.domain.store.repository.StoreRepository;
import com.example.umc9th_week5.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewResDTO.ReviewInfo addReview(ReviewReqDTO.reviewReqDTOForAddReview dto) {
        Store store = storeRepository.findById(dto.store().id()).orElseThrow(() -> new GeneralException(StoreErrorCode.NOT_FOUND));
        Member member = memberRepository.findById(dto.member().id()).orElseThrow(() -> new GeneralException(MemberErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReviewEntity(dto, store, member);
        reviewRepository.save(review);
        return ReviewConverter.toReviewResDTO(review);
    }
}
