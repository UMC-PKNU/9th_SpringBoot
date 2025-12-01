package com.example.umc.domain.review.service.query;

import com.example.umc.domain.review.dto.res.ReviewResDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewQueryService {

    ReviewResDto.ReviewPreViewListDto findReview(Long memberId, String storeName, Integer page);

    void updateReview(Long reviewId, Double newRating, String newContent);

//    Page<ReviewResDto.ReviewInfo> getReviews(Long memberId, String storeName, Double rating);
}
