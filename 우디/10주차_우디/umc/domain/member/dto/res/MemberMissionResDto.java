package com.example.umc.domain.member.dto.res;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDto {

    @Builder
    public record MemberMissionDto (
            Long memberMissionId,
            String status,
            Integer moneyLowerLimit,
            Integer point,
            String storeName
    ){}

    @Builder
    public record GetMemberMissionsDto (
            List<MemberMissionDto> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record CreateResultDto (
            @NotBlank
            Long memberMissionId,
            @NotBlank
            LocalDateTime createdAt
    ){}
}
