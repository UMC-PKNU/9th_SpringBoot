package com.example.umc9th_week5.domain.mission.service.command;

import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.mission.code.MemberMissionErrorCode;
import com.example.umc9th_week5.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th_week5.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th_week5.domain.mission.entity.MemberMission;
import com.example.umc9th_week5.domain.mission.entity.Mission;
import com.example.umc9th_week5.domain.mission.enums.MissionStatus;
import com.example.umc9th_week5.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th_week5.global.apiPayload.exception.GeneralException;
import com.example.umc9th_week5.global.validation.GlobalValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{
    private final GlobalValidatorService globalValidatorService;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMissionResDTO.MemberMissionInfo missionChallenge(MemberMissionReqDTO.memberMissionReqDTOForChallenge dto) {
        Member member = globalValidatorService.validateExistMemberById(dto.memberId());
        Mission mission = globalValidatorService.validateExistMissionById(dto.missionId());

        if(memberMissionRepository.findByMemberIdAndMissionId(dto.memberId(), dto.missionId()).isPresent()){
            throw new GeneralException(MemberMissionErrorCode.ALREADY_EXIST);
        }

        MemberMission memberMissionEntity = MemberMissionConverter.toMemberMissionEntity(member, mission);

        MemberMission saved = memberMissionRepository.save(memberMissionEntity);
        return MemberMissionConverter.toMemberMissionDTO(saved);
    }

    @Override
    @Transactional
    public MemberMissionResDTO.MemberMissionInfoList completeMission(Long memberId, Long missionId, int page) {
        Member member = globalValidatorService.validateExistMemberById(memberId);
        globalValidatorService.validateExistMissionById(missionId);

        MemberMission memberMission = globalValidatorService.validateExistMemberMissionByMemberIdAndMissionId(memberId, missionId);
        memberMission.updateStatus(MissionStatus.COMPLETED);
        memberMissionRepository.flush();
//        memberMissionRepository.save(memberMission);

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<MemberMission> allByMemberAndStatus = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.COMPLETED, pageRequest);
        return MemberMissionConverter.toMemberMissionListDTO(allByMemberAndStatus);
    }
}
