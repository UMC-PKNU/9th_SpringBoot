package com.example.umc.domain.member.dto.res;

import com.example.umc.domain.mission.dto.MissionReqDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberMissionResDto {

    @Builder
    public record CreateResultDto (
            @NotBlank
            Long memberMissionId,
            @NotBlank
            LocalDateTime createdAt
    ){}
}
