package com.example.umc9th_week5.global.auth.details;

import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.global.validator.GlobalValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final GlobalValidatorService globalValidatorService;

    @Override
    public UserDetails loadUserByUsername(
            String email    //username인데 편의상 email로 변경
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        Member member = globalValidatorService.validateExistMemberByEmail(email);
        // CustomUserDetails 반환
        return new CustomUserDetails(member);
    }

}
