package com.example.umc_9th_spring.domain.review.service.command;

import com.example.umc_9th_spring.domain.review.converter.ReviewConverter;
import com.example.umc_9th_spring.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.entity.Review;
import com.example.umc_9th_spring.domain.review.repository.ReviewRepository;
import com.example.umc_9th_spring.domain.store.code.StoreErrorCode;
import com.example.umc_9th_spring.domain.store.entity.Store;
import com.example.umc_9th_spring.domain.store.exception.StoreException;
import com.example.umc_9th_spring.domain.store.repository.StoreRepository;
import com.example.umc_9th_spring.domain.user.code.UserErrorCode;
import com.example.umc_9th_spring.domain.user.entity.User;
import com.example.umc_9th_spring.domain.user.exception.UserException;
import com.example.umc_9th_spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.ReviewInfo createReview(Long userId, Long storeId, ReviewReqDTO.CreateReview request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toEntity(request, user, store);
        reviewRepository.save(review);

        return ReviewConverter.toReviewInfoDTO(review);
    }
}

