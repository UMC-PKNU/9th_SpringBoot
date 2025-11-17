package com.example.umc.domain.member.dto.res;

import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.store.enums.FoodCategory;
import lombok.Builder;
import lombok.Getter;

public class MemberResDto {

    @Getter
    @Builder
    public static class GetMemberMissions {
        private MissionStatus status;
        private Long moneyLowerLimit;
        private Long point;
        private String storeName;
    }

    @Getter
    @Builder
    public static class GetMyPage {
        private String nickname;
        private String email;
        private String phoneNumber;
        private Long point;
    }

    @Getter
    @Builder
    public static class AvailableMission {
        private String nickname;
        private FoodCategory foodCategory;
        private int moneyLowerLimit;
        private int period;
        private MissionStatus missionStatus;
    }

}
