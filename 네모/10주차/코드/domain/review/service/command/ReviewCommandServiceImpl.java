package com.example.umc9th_week5.domain.review.service.command;

import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.review.converter.ReviewConverter;
import com.example.umc9th_week5.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_week5.domain.review.entity.Review;
import com.example.umc9th_week5.domain.review.repository.ReviewRepository;
import com.example.umc9th_week5.domain.store.entity.Store;
import com.example.umc9th_week5.global.validator.GlobalValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final GlobalValidatorService globalValidatorService;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.ReviewInfo addReview(ReviewReqDTO.reviewReqDTOForAddReview dto) {
        Store store = globalValidatorService.validateExistStoreById(dto.storeId());
        Member member = globalValidatorService.validateExistMemberById(dto.memberId());

        Review review = ReviewConverter.toReviewEntity(dto, store, member);
        reviewRepository.save(review);
        return ReviewConverter.toReviewInfoDTO(review);
    }
}
