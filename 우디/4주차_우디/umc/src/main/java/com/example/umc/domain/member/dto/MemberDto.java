package com.example.umc.domain.member.dto;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.entity.mapping.MemberMission;
import com.example.umc.domain.member.enums.MemberStatus;
import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.store.enums.FoodCategory;
import com.example.umc.domain.store.mapping.Food;
import lombok.*;

@Getter
@Builder
public class MemberDto {

    private String nickname;
    private String email;
    private String phoneNumber;
    private Long point;
    private FoodCategory food;
    private int period;
    private int moneyLowerLimit;
    private MissionStatus missionStatus;

    public static MemberDto from1(Member member) {
        return MemberDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point((long) member.getPoint())
                .build();
    }

    // 빌더패턴 공부하기
    public static MemberDto from2(Member member, MemberMission memberMission, Food food, Mission mission) {
        return MemberDto.builder()
                .nickname(member.getNickname())
                .food(food.getFood())
                .moneyLowerLimit(mission.getMoneyLowerLimit())
                .period(mission.getPeriod())
                .missionStatus(memberMission.getStatus())
                .build();
    }
}
