package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;


    @GetMapping("/reviews/{memberId}/byStoreNameAndRating")
    public ApiResponse<List<ReviewResDto.ReviewResDefaultDto>> findReviewsByStoreAndRating(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Long rating
    ){
    List<Review> reviewList = reviewQueryService.findReviewsByStoreAndRating(memberId,storeName, rating);
    List<ReviewResDto.ReviewResDefaultDto> convertedReviews = reviewList.stream()
            .map(ReviewConverter::toReviewResDefaultDTO) // 💡 ReviewConverter에 엔티티 객체(Review) 전달
            .collect(Collectors.toList());
    // ApiResponse로 래핑하여 반환
    GeneralSuccessCode code = GeneralSuccessCode.OK;


    return ApiResponse.onSuccess(
                    code,
                    convertedReviews
            );
    }


}

//6주차
//package com.example.umc9th.controller;
//
//import com.example.umc9th.domain.review.dto.ReviewResponseDto;
//import com.example.umc9th.domain.review.entity.Review;
//import com.example.umc9th.domain.review.service.ReviewQueryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class ReviewController {
//
//    private final ReviewQueryService reviewQueryService;
//    @GetMapping("/reviews/search")
//    public List<Review> searchReview(
//            @RequestParam String query,
//            @RequestParam String type
//    ){
//        List<Review> result = reviewQueryService.searchReview(query, type);
//        return result;
//    }
//
//    @GetMapping("/reviews/{mem_id}/byStoreNameAndRating")
//    public List<ReviewResponseDto> findReviewsByStoreAndRating(
//            @PathVariable Long mem_id,
//            @RequestParam(required = false) String store_name,
//            @RequestParam(required = false) Long rating
//    ){
//        List<ReviewResponseDto> result = reviewQueryService.findReviewsByStoreAndRating(mem_id,store_name, rating);
//        return result;
//    }
//}
