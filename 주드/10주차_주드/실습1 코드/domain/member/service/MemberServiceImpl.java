package com.example.umc9th.domain.member.service;

import ch.qos.logback.core.status.ErrorStatus;
import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    // Password Encoder
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Page<MemberMission> findMyProgressMissions(Long userId, Integer page) {
        // 1. 유저 확인
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)); // 예외 처리

        // 2. 페이징 설정 (0 이하 처리)
        int pageIndex = (page <= 0) ? 0 : page - 1;
        PageRequest pageRequest = PageRequest.of(pageIndex, 10);

        // 3. Repository 호출 (Enum 사용)
        return memberMissionRepository.findAllByMemberIdAndStatus(userId, MemberMissionStatus.PROGRESS, pageRequest);
    }
    @Override
    @Transactional
    public Member joinMember(MemberReqDTO.JoinDto request) {
        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(request.password());

        // 1. 이메일 중복 검사
        if (memberRepository.existsByEmail(request.email())) {
            // 예외 발생! -> Controller까지 안 가고 여기서 멈춤 -> ExceptionAdvice가 잡음
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXIST);
        }

        // 2. 닉네임 중복 검사
        if (memberRepository.existsByNickname(request.nickname())) {
            throw new MemberException(MemberErrorCode.NICKNAME_ALREADY_EXIST);
        }

        // 3. 전화번호 중복 검사
        // (하이픈 제거 후 검사하는 로직이 필요하다면 여기서 가공 후 체크)
        String rawPhone = request.phoneNumber();
        if (memberRepository.existsByPhoneNumber(rawPhone)) {
            throw new MemberException(MemberErrorCode.PHONE_NUMBER_ALREADY_EXIST);
        }
        // DTO -> Entity &#xBCC0;&#xD658;
        Member newMember = MemberConverter.toMember(request,salt, Role.ROLE_USER);

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

    @Override
    @Transactional
    public MemberMission challengeMission(MemberMissionReqDTO.MissionAcceptDto request, Long userId) {

        // 1. 유저 확인
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 미션 확인
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MemberException(MemberMissionErrorCode.MISSION_NOT_FOUND));

        // 3. 검증: 이미 도전 중인 미션인지 확인
        if (memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(userId, mission.getId(), MemberMissionStatus.PROGRESS)) {
            throw new MemberException(MemberMissionErrorCode.MISSION_ALREADY_PROGRESSING); // 에러 코드 추가 필요
        }

        // 4. 매핑 엔티티 생성
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MemberMissionStatus.PROGRESS) // 진행중 상태로 시작
                .due_date(LocalDateTime.now().plusDays(7)) // 예: 마감기한 7일 뒤
                .created_at(LocalDateTime.now())
                .build();

        return memberMissionRepository.save(memberMission);
    }
}