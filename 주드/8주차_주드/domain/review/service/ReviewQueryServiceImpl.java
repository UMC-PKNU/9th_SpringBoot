package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.umc9th.domain.member.entity.QMember.member;
import static com.example.umc9th.domain.mission.entity.QMission.mission;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    // post요청 관련서비스는 commandservice로 따로 작성하라함 queryservice는 get요청용
    @Override
    @Transactional
    public Review createReview(Long userId, Long storeId, ReviewReqDto.ReviewCreateDto request) {

        // 1. 유저 확인
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 미션 확인
        Mission mission = missionRepository.findById(storeId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_ID_NOT_FOUND)); // MissionHandler가 없으면 일단 MemberHandler 사용 (나중에 수정 권장)

        // 3. 미션 수행 가게 확인
        Store store = mission.getStore();

        // 4. 리뷰 생성 및 저장
        Review review = ReviewConverter.toReview(request, member, store);
        return reviewRepository.save(review);
    }

    public List<Review> findReviewsByStoreAndRating(
            Long memberId,
            String storeName,
            Long rating
    ) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // mem_id는 @PathVariable이라 조건 달 필요없이 그냥 필수라함
//        if (mem_id == null) {
//            // 비즈니스 로직에 따라 NullPointerException 또는 IllegalArgumentException을 던질 수 있습니다.
//            //throw new IllegalArgumentException("회원 ID(mem_id)는 필수 조건입니다.");
//            throw new ReviewException(ReviewErrorCode.REVIEW_MEMBER_ID_REQUIRED);
//        }

        builder.and(review.member.id.eq(memberId));

        if (storeName == null){
            throw new ReviewException(ReviewErrorCode.REVIEW_STORE_ID_REQUIRED);
        }
        if (rating == null) {
            throw new ReviewException(ReviewErrorCode.REVIEW_RATING_REQUIRED);
        }
        builder.and(review.store.name.eq(storeName));
        builder.and(review.rating.eq(rating));

        List<Review> reviewList = reviewRepository.findReviewsByStoreAndRating((builder));

        return reviewList;

    }
    //6주차
//    @Transactional(readOnly = true) // ⭐ DTO 변환을 위한 트랜잭션 설정
//    public List<ReviewResponseDto> findReviewsByStoreAndRating(
//            Long mem_id,
//            String store_name,
//            Long rating
//    ) {
//        QReview review = QReview.review;
//        BooleanBuilder builder = new BooleanBuilder();
//
//        if (mem_id == null) {
//            // 비즈니스 로직에 따라 NullPointerException 또는 IllegalArgumentException을 던질 수 있습니다.
//            throw new IllegalArgumentException("회원 ID(mem_id)는 필수 조건입니다.");
//        }
//
//        builder.and(review.member.id.eq(mem_id));
//
//        if (store_name != null){
//            builder.and(review.store.name.eq(store_name));
//        }
//        if (rating != null) {
//            // rating 값이 있으면 정확히 일치하는 리뷰를 필터링합니다.
//            // null이면 해당 멤버가 쓴 리뷰 전체 출력
//            builder.and(review.rating.eq(rating));
//        }
//
//        List<Review> reviewList = reviewRepository.findReviewsByStoreAndRating((builder));
//
//        return reviewList.stream()
//                .map(ReviewResponseDto::from) // DTO의 팩토리 메서드를 사용해 변환
//                .collect(Collectors.toList());
//
//    }
}
