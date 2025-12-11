package com.example.umc9th_week5.domain.mission.service.query;

import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th_week5.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th_week5.domain.mission.entity.MemberMission;
import com.example.umc9th_week5.domain.mission.enums.MissionStatus;
import com.example.umc9th_week5.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th_week5.global.validator.GlobalValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService{
    private final GlobalValidatorService globalValidatorService;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMissionResDTO.MemberMissionInfoList getInProgressMissions(Long memberId, int page) {
        Member member = globalValidatorService.validateExistMemberById(memberId);

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<MemberMission> allByMemberAndStatus = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.IN_PROGRESS, pageRequest);

        return MemberMissionConverter.toMemberMissionListDTO(allByMemberAndStatus);
    }
}
