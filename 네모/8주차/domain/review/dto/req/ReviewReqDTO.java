package com.example.umc9th_week5.domain.review.dto.req;

import com.example.umc9th_week5.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th_week5.domain.store.dto.req.StoreReqDTO;
import lombok.Builder;

public class ReviewReqDTO {
    @Builder
    public record reviewReqDTO(
            StoreReqDTO.storeReqDTO store,
            MemberReqDTO.memberReqDTO member,
            Float rating,
            String content
    ){}

    @Builder
    public record reviewReqDTOForAddReview(
            MemberReqDTO.memberReqDTOForAddReview member,
            StoreReqDTO.storeReqDTOForAddReview store,
            Float rating,
            String content
    ){}
}
