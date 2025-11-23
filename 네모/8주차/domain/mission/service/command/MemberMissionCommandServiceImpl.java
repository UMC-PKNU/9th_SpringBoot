package com.example.umc9th_week5.domain.mission.service.command;

import com.example.umc9th_week5.domain.member.code.MemberErrorCode;
import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.member.repository.MemberRepository;
import com.example.umc9th_week5.domain.mission.code.MissionErrorCode;
import com.example.umc9th_week5.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th_week5.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th_week5.domain.mission.entity.MemberMission;
import com.example.umc9th_week5.domain.mission.entity.Mission;
import com.example.umc9th_week5.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th_week5.domain.mission.repository.MissionRepository;
import com.example.umc9th_week5.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMissionResDTO.MemberMissionInfo missionChallenge(MemberMissionReqDTO.memberMissionReqDTOForChallenge dto) {
        Member member = memberRepository.findById(dto.member().id()).orElseThrow(() -> new GeneralException(MemberErrorCode.NOT_FOUND));
        Mission mission = missionRepository.findById(dto.mission().id()).orElseThrow(() -> new GeneralException(MissionErrorCode.NOT_FOUND));

        MemberMission memberMissionEntity = MemberMissionConverter.toMemberMissionEntity(member, mission);

        MemberMission saved = memberMissionRepository.save(memberMissionEntity);
        return MemberMissionConverter.toMemberMissionDTO(saved);
    }
}
