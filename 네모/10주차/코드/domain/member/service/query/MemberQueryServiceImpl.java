package com.example.umc9th_week5.domain.member.service.query;

import com.example.umc9th_week5.domain.member.code.MemberErrorCode;
import com.example.umc9th_week5.domain.member.converter.MemberConverter;
import com.example.umc9th_week5.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.member.exception.MemberException;
import com.example.umc9th_week5.domain.member.repository.MemberRepository;
import com.example.umc9th_week5.global.auth.details.CustomUserDetails;
import com.example.umc9th_week5.global.auth.util.JwtUtil;
import com.example.umc9th_week5.global.validator.GlobalValidatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{
    private final MemberRepository memberRepository;
    private final GlobalValidatorService globalValidatorService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MemberResDTO.LoginDTO login(MemberReqDTO.@Valid LoginDTO dto) {
        // Member 조회
        Member member = globalValidatorService.validateExistMemberByEmail(dto.email());

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member, accessToken);
    }
}