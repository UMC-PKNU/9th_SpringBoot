package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Member joinMember(MemberReqDTO.JoinDto request) {

        // 1. 이메일 중복 검사
        if (memberRepository.existsByEmail(request.getEmail())) {
            // 예외 발생! -> Controller까지 안 가고 여기서 멈춤 -> ExceptionAdvice가 잡음
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXIST);
        }

        // 2. 닉네임 중복 검사
        if (memberRepository.existsByNickname(request.getNickname())) {
            throw new MemberException(MemberErrorCode.NICKNAME_ALREADY_EXIST);
        }

        // 3. 전화번호 중복 검사
        // (하이픈 제거 후 검사하는 로직이 필요하다면 여기서 가공 후 체크)
        String rawPhone = request.getPhoneNumber();
        if (memberRepository.existsByPhoneNumber(rawPhone)) {
            throw new MemberException(MemberErrorCode.PHONE_NUMBER_ALREADY_EXIST);
        }
        // DTO -> Entity &#xBCC0;&#xD658;
        Member newMember = MemberConverter.toMember(request);

        // DB 저장
        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public Member findMember(Long userId) {
        return memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    // 홈 화면 데이터 조회 통합 메서드
    @Override
    @Transactional
    public List<MemberMission> findMyProgressMissions(Long userId) {
        return memberMissionRepository.findProgressMissions(userId);
    }
    @Override
    @Transactional
    public List<MemberMission> findMyCompleteMissions(Long userId) {
        return memberMissionRepository.findCompleteMissions(userId);
    }
    @Override
    @Transactional
    public Integer getCompletedMissionCount(Long userId) {
        return memberMissionRepository.countCompletedMissions(userId);
    }
}