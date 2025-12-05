package com.example.umc9th.domain.member.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDTO {
        Long memberMissionId; // 생성된 MemberMission의 ID
        LocalDateTime createdAt; // 생성 시간 (수락 시간)
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionListDTO {
        List<MyMissionDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionDTO {
        Long memberMissionId; // 매핑 ID
        Long missionId;       // 원본 미션 ID
        String missionName;
        String missionContent;
        Integer reward;
        LocalDate createdAt;  // 도전 시작일
    }
}
