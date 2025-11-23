package com.example.umc.domain.member.converter;

import com.example.umc.domain.member.dto.req.MemberReqDto;
import com.example.umc.domain.member.dto.res.MemberResDto;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.entity.mapping.MemberMission;
import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.food.enums.FoodCategory;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDto.JoinDto toJoinDto(Member member) {
        return MemberResDto.JoinDto.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(MemberReqDto.JoinDto dto) {
        return Member.builder()
                .username(dto.name())
                .birth(dto.birth())
                .gender(dto.gender())
                .address(dto.address())
                .position(dto.position())
                .build();
    }




    // 기존의 MemberMissionDto 클래스의 from 메서드를 리펙토링함
    // 파라미터로 오브젝트말고 객체를 받아오는걸로 수정해야됨
    // 레파지토리쪽 코드 같이 수정해야됨
    public static MemberResDto.GetMemberMissions toGetMemberMissionDto(MemberMission memberMission) {
        return MemberResDto.GetMemberMissions.builder()
                .status(memberMission.getStatus())
                .moneyLowerLimit((long) memberMission.getMission().getMoneyLowerLimit())
                .point((long) memberMission.getMission().getPoint())
                .storeName(memberMission.getMission().getStore().getName())
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
