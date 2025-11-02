package com.example.umc_9th_.domain.user.controller;

import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.repository.ReviewRepository;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    // 마이페이지 기본 정보 조회
    @GetMapping("/{userId}/mypage")
    public User getMyPage(@PathVariable Long userId) {
        return userRepository.findUserMyPageInfo(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    }

    // 작성한 리뷰 조회
    @GetMapping("/{userId}/reviews")
    public List<Review> getMyReviews(@PathVariable Long userId) {
        return reviewRepository.findReviewsByUserId(userId);
    }
}