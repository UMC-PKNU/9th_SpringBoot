package com.example.umc.domain.member.service.query;

import com.example.umc.domain.member.dto.req.MemberReqDto;
import com.example.umc.domain.member.dto.res.MemberMissionResDto;
import com.example.umc.domain.member.dto.res.MemberResDto;
import com.example.umc.domain.member.enums.MissionStatus;
import jakarta.validation.Valid;

public interface MemberQueryService {

    void checkFlag(Long flag);

    // 마이페이지 조회
    MemberResDto.GetMyPage getMyPage(Long memberId);

    // 내가 진행하고 있는 미션목록 조회
    MemberMissionResDto.GetMemberMissionsDto getMyMissions(Long memberId, MissionStatus status, Integer page);


    MemberResDto.LoginDto login(MemberReqDto.LoginDto dto);
}
