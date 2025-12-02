package com.example.umc9th.domain.mission.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {
    // 단일 미션 정보
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionResPreViewDTO {
        private Long missionId;
        private String name; // 미션이름
        private Long reward;
        private String content;
        private LocalDate deadline;
        private String local;
    }

    // 리스트 응답 (페이징 정보 포함)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionResListDTO {
        private List<MissionResPreViewDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
