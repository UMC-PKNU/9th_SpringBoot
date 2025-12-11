package com.example.umc.domain.review.dto.req;

public class ReviewReqDto {

    public record JoinDto (
            Long storeId,
            String content,
            Double rating,
            String imageUrl
    ){}
}
