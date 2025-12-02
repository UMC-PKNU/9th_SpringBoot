package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews")
    @Operation(summary = "내가 작성한 가게 리뷰 목록 조회 API", description = "특정 가게에 대해 내가 작성한 리뷰들의 목록을 조회합니다. 쿼리로 page 번호,멤버,가게id 기입 (page 1번부터 시작).")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    public ApiResponse<ReviewResDto.ReviewResListDTO> getMyReviews(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page
    ) {

        Page<Review> reviewPage = reviewQueryService.getMyReviewsByStore(memberId, storeId, page);

        ReviewResDto.ReviewResListDTO responseDto = ReviewConverter.toReviewResListDTO(reviewPage);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseDto);
    }



    @GetMapping("/reviews/{memberId}/byStoreNameAndRating")
    public ApiResponse<List<ReviewResDto.ReviewResDefaultDto>> findReviewsByStoreAndRating(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Long rating
    ){
    List<Review> reviewList = reviewQueryService.findReviewsByStoreAndRating(memberId,storeName, rating);
    List<ReviewResDto.ReviewResDefaultDto> convertedReviews = reviewList.stream()
            .map(ReviewConverter::toReviewResDefaultDTO)
            .collect(Collectors.toList());

    GeneralSuccessCode code = GeneralSuccessCode.OK;


    return ApiResponse.onSuccess(
                    code,
                    convertedReviews
            );
    }
    // 리뷰 작성 API
    @PostMapping("/reviews/{userId}/stores/{storeId}")
    public ApiResponse<ReviewResDto.CreateReviewResultDTO> createReview(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody
            @Valid ReviewReqDto.ReviewCreateDto request
    ) {
        // 1. 서비스 호출
        Review review = reviewQueryService.createReview(userId, storeId, request);

        // 2. 응답 반환 (201 Created)
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, ReviewConverter.toCreateReviewResultDTO(review));
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
