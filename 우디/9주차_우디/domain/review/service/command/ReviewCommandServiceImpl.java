package com.example.umc.domain.review.service.command;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.exception.MemberException;
import com.example.umc.domain.member.exception.code.MemberErrorCode;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.review.converter.ReviewConverter;
import com.example.umc.domain.review.dto.req.ReviewReqDto;
import com.example.umc.domain.review.dto.res.ReviewResDto;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.exception.StoreException;
import com.example.umc.domain.store.exception.code.StoreErrorCode;
import com.example.umc.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public ReviewResDto.JoinDto createReview(Long memberId, ReviewReqDto.JoinDto dto) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(Math.toIntExact(dto.storeId()))
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 클라이언트에서 받은 DTO를 엔티티로 변환
        Review review = ReviewConverter.toReview(dto, member, store);
        reviewRepository.save(review);

        return ReviewConverter.toJoinDto(review);
    }
}
