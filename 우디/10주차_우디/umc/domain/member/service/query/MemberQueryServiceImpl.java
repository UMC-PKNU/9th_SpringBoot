package com.example.umc.domain.member.service.query;

import com.example.umc.domain.member.converter.MemberConverter;
import com.example.umc.domain.member.converter.MemberMissionConverter;
import com.example.umc.domain.member.dto.req.MemberReqDto;
import com.example.umc.domain.member.dto.res.MemberMissionResDto;
import com.example.umc.domain.member.dto.res.MemberResDto;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.entity.mapping.MemberMission;
import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.member.exception.MemberException;
import com.example.umc.domain.member.exception.code.MemberErrorCode;
import com.example.umc.domain.member.repository.MemberMissionRepository;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.mission.repository.MissionRepository;
import com.example.umc.global.auth.principal.CustomUserDetails;
import com.example.umc.global.auth.principal.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MemberResDto.LoginDto login(
            MemberReqDto.@Valid LoginDto dto
    ) {

        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDto(member, accessToken);
    }

    @Override
    public void checkFlag(Long flag) {
        if (flag == null)
            return;
        if (flag == 1) {
            throw new MemberException(MemberErrorCode.UNAUTHORIZED);
        } else if (flag == 2) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }
    }

    @Override
    public MemberResDto.GetMyPage getMyPage(Long memberId) {
        Optional<Member> OptionalMember = memberRepository.findById(memberId);

        Member member = OptionalMember.orElseThrow(()
                -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetMyPageDto(member);
    }

    @Override
    public MemberMissionResDto.GetMemberMissionsDto getMyMissions(Long memberId, MissionStatus status, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);

        Page<MemberMission> result = memberMissionRepository.findMemberMissions(memberId, status, pageRequest);

        return MemberMissionConverter.toGetMemberMissionsDto(result);
    }
}

