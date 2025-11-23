package com.example.umc9th_week5.domain.member.dto.req;

import com.example.umc9th_week5.domain.member.enums.Gender;
import lombok.Builder;

import java.time.LocalDate;

public class MemberReqDTO {
    @Builder
    public record memberReqDTO(
       String name,
       Gender gender,
       LocalDate birth,
       String address
    ){}

    @Builder
    public record memberReqDTOForAddReview(
            Long id
    ){}

    @Builder
    public record memberReqDTOForChallenge(
            Long id
    ){}
}
