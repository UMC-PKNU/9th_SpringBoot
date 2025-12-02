package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


public class MemberConverter {

    // 결과값(DTO) 생성
    public static MemberResDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now()) // 엔티티의 created_at이 null일 경우 대비
                .build();
    }

    // 회원가입 요청(DTO) -> 엔티티 변환
    public static Member toMember(MemberReqDTO.JoinDto request) {

        // Gender 문자열을 Enum으로 변환 (예외처리 필요할 수 있음)
        Gender gender = null;
        try {
            gender = Gender.valueOf(request.getGender().toUpperCase());
        } catch (IllegalArgumentException e) {
            gender = Gender.MALE;
        }

        return Member.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .gender(gender)
                .local(request.getLocal())
                .point(0L) // 기본값 설정
                .is_active(true) // 기본값 설정
                .build();
    }
    public static MemberResDTO.HomeViewDTO toHomeViewDTO(Member member, List<MemberMission> myMissions, Integer completedCount) {

        // 미션 리스트 변환
        List<MemberResDTO.MyMissionDTO> missionDTOs = myMissions.stream()
                .map(MemberConverter::toMyMissionDTO)
                .collect(Collectors.toList());

        return MemberResDTO.HomeViewDTO.builder()
                .nickname(member.getNickname())
                .local(member.getLocal())     // 안암동
                .point(member.getPoint())     // 999,999
                .completedMissionCount(completedCount) // 7 (이걸로 7/10 표현)
                .myMissionList(missionDTOs)
                .build();
    }

    // 개별 미션 변환 (D-Day 계산 로직 포함)
    public static MemberResDTO.MyMissionDTO toMyMissionDTO(MemberMission memberMission) {

        // D-Day 계산
        long days = ChronoUnit.DAYS.between(LocalDate.now(), memberMission.getDue_date().toLocalDate());
        String dDayStr = (days >= 0) ? "D-" + days : "D+" + Math.abs(days); // 지났으면 D+

        return MemberResDTO.MyMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionContent(memberMission.getMission().getContent())
                .reward(memberMission.getMission().getReward())
                .dDay(dDayStr)
                .build();
    }

    // 진행중인 미션 리스트 변환(단일 미션 변환 재사용)
    public static List<MemberResDTO.MyMissionDTO> toMyMissionDTOList(List<MemberMission> memberMissionList) {
        return memberMissionList.stream()
                .map(MemberConverter::toMyMissionDTO) // 위의 단일 변환 메서드 재사용
                .collect(Collectors.toList());
    }

    public static MemberMissionResDTO.CreateResultDTO toCreateResultDTO(MemberMission memberMission) {
        return MemberMissionResDTO.CreateResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreated_at())
                .build();
    }
}

