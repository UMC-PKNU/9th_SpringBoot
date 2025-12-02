package com.example.umc9th.domain.member.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        private Long memberId;
        private LocalDateTime createdAt;
    }
    // 홈 화면 전체 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeViewDTO {
        String nickname;      // 유저 닉네임
        String local;         // 지역 (안암동)
        Long point;           // 보유 포인트 (999,999)
        Integer completedMissionCount; // 완료한 미션 수 (7) - 진행도 계산용
        List<MyMissionDTO> myMissionList; // 하단 "MY MISSION" 리스트
    }
    // 하단 미션 리스트 아이템
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionDTO {
        Long memberMissionId; // API 호출용 ID
        String storeName;     // 가게 이름 (반이학생마라탕)
        String missionContent;   // 미션 내용 (10,000원 이상 식사 시)
        Long reward;       // 보상 (500P)
        String dDay;          // 마감기한 (D-7)
    }

}
