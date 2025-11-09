package com.example.umc.domain.member.entity;

import com.example.umc.domain.member.MemberDto;
import com.example.umc.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto getMyPageInfo(Long memberId, String nickname, String email, String phoneNumber, int point) {
        return memberRepository.findMyPageDtoById(memberId);
    }
}
