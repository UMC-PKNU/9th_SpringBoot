package com.example.umc.domain.member.converter;

import com.example.umc.domain.member.dto.res.MemberResDto;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.store.enums.FoodCategory;

// 객체 -> DTO
public class MemberConverter {

    // 기존의 MemberMissionDto 클래스의 from 메서드를 리펙토링함
    // 파라미터로 오브젝트말고 객체를 받아오는걸로 수정해야됨
    // 레파지토리쪽 코드 같이 수정해야됨
    public static MemberResDto.GetMemberMissions toGetMemberMissionDto(Object[] result) {
        return MemberResDto.GetMemberMissions.builder()
                .status((MissionStatus) result[0])
                .moneyLowerLimit((long) result[1])
                .point((long) result[2])
                .storeName((String) result[3])
                .build();
    }

    // MemberDto 클래스의 from1 메서드 리펙토링
    public static MemberResDto.GetMyPage toGetMyPageDto(Member member) {
        return MemberResDto.GetMyPage.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point((long) member.getPoint())
                .build();
    }

    // MemberDto 클래스의 from2 메서드 리펙토링
    // Page 사용해서 나중에 바꿔야함
    public static MemberResDto.AvailableMission toAvailableMissionDto(Object[] result) {
        return MemberResDto.AvailableMission.builder()
                .nickname((String) result[0])
                .foodCategory((FoodCategory) result[1])
                .moneyLowerLimit((int) result[2])
                .period((int) result[3])
                .missionStatus((MissionStatus) result[4])
                .build();
    }
}
