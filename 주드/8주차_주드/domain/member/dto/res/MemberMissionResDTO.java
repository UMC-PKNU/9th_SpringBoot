package com.example.umc9th.domain.member.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberMissionResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDTO {
        Long memberMissionId; // 생성된 MemberMission의 ID
        LocalDateTime createdAt; // 생성 시간 (수락 시간)
    }
}
