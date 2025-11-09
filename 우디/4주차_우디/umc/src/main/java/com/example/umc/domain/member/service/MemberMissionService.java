package com.example.umc.domain.member.service;

import com.example.umc.domain.member.dto.MemberMissionDto;
import com.example.umc.domain.member.repository.MemberMissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;

    public Page<MemberMissionDto> getMemberMissionByStatus(String status, Pageable pageable) {
        Page<Object[]> result = memberMissionRepository.findMemberMissionsWithStatus(status, pageable);

        Page<MemberMissionDto> dto = result.map(MemberMissionDto::from);
        return dto;
    }
}
