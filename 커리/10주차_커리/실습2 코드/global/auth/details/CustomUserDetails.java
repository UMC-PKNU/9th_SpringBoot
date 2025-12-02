package com.example.umc_9th_spring.global.auth.details;

import com.example.umc_9th_spring.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    //1. 요청이 들어온 후, 인증이 필요한 객체 (UserDetails) 를 생성하기.
    //2. AuthenticationManager를 통해 UserDetails를 인증할 서비스 UserDetailsService 찾기 (여기선 implements한 CustomUserDetailsService)
    //3. 그 중 인증해줄 UserDetailsService를 이용해 UserDetails를 새로 생성한다
    //4. UserDetails를 Authentication으로 변경후 SecurityContext에 넣는다

    private final User user;

    //권한을 List 형태로 반환 (ADMIN or USER)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> user.getRole().toString());
    }


    //비밀번호를 반환
    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); //아이디를 반환 (여기선 이메일을 아이디로 사용!)
    }
}
