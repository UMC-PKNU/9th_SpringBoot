package com.example.umc.domain.member.service.query;

import com.example.umc.domain.member.dto.res.MemberResDto;
import com.example.umc.domain.member.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryService {

    void checkFlag(Long flag);

    // 마이페이지 조회
    MemberResDto.GetMyPage getMyPage(Long memberId);

    // 내가 진행하고 있는 미션목록 조회
    Page<MemberResDto.GetMemberMissions> getMyMissions(Long memberId, MissionStatus status, Pageable pageable);


}
