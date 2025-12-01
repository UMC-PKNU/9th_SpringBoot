package com.example.umc.domain.member.service.command;

import com.example.umc.domain.member.converter.MemberMissionConverter;
import com.example.umc.domain.member.dto.res.MemberMissionResDto;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.entity.mapping.MemberMission;
import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.member.exception.MemberException;
import com.example.umc.domain.member.exception.MemberMissionException;
import com.example.umc.domain.member.exception.code.MemberErrorCode;
import com.example.umc.domain.member.exception.code.MemberMissionErrorCode;
import com.example.umc.domain.member.repository.MemberMissionRepository;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.mission.dto.MissionReqDto;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.exception.MissionException;
import com.example.umc.domain.mission.exception.code.MissionErrorCode;
import com.example.umc.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMissionResDto.CreateResultDto challengeMission(Long memberId, MissionReqDto.ChallengeMissionDto dto) {

        // 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 미션 조회
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 이미 도전중인지 검증
        boolean isAlreadyChallenging = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                member.getId(),
                mission.getId(),
                MissionStatus.IN_PROGRESS
        );

        if (isAlreadyChallenging)
            throw new MemberMissionException(MemberMissionErrorCode.MISSION_ALREADY_CHALLENGING);

        // 테이블에 저장 (도전 시작)
        // 컨버터에 나두는거 생각해보자
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();

        memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toCreateResultDto(memberMission);
    }
}
