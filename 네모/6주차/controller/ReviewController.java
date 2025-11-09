package com.example.umc9th_week5.domain.review.controller;

import com.example.umc9th_week5.domain.review.dto.res.ReviewResDto;
import com.example.umc9th_week5.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("search")
    public List<ReviewResDto> searchReview(@RequestParam(required=false) String storeName,
                                           @RequestParam(required=false) Float rate){

        return reviewQueryService.searchReview(storeName, rate);
    }
}
