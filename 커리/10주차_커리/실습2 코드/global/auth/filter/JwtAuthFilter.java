package com.example.umc_9th_spring.global.auth.filter;

import com.example.umc_9th_spring.global.apiPayLoad.ApiResponse;
import com.example.umc_9th_spring.global.apiPayLoad.code.GeneralErrorCode;
import com.example.umc_9th_spring.global.auth.details.CustomUserDetailsService;
import com.example.umc_9th_spring.global.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
HTTP 요청
 → Authorization 헤더에서 JWT 추출
 → JWT가 유효한지 검증
 → JWT 안에 있는 사용자 정보(email)를 꺼냄
 → 그 email 로 UserDetails 찾아옴
 → Authentication 객체 생성
 → SecurityContextHolder에 저장
 → (이제 요청은 인증된 사용자로 처리됨)

 */

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil; //토큰 검증 & 파싱
    private final CustomUserDetailsService customUserDetailsService; //DB에서 실제 사용자 정보 얻기 → UserDetails 객체는 CustomUserDetailsService.loadUserByUsername(email) 을 통해 만들어짐.

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
            // 토큰 가져오기
            String token = request.getHeader("Authorization");
            // token이 없거나 Bearer가 아니면 넘기기
            if (token == null || !token.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            // Bearer이면 추출
            token = token.replace("Bearer ", "");
            // AccessToken 검증하기: 올바른 토큰이면
            if (jwtUtil.isValid(token)) {
                // 토큰에서 이메일 추출
                String email = jwtUtil.getEmail(token);
                // 인증 객체 생성: 이메일로 찾아온 뒤, 인증 객체 생성
                UserDetails user = customUserDetailsService.loadUserByUsername(email);
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        user, //첫 번째 인자: Principal (보통 UserDetails)
                        null, //두 번째 인자: Credentials (비밀번호) → JWT 인증이므로 null.
                        user.getAuthorities() //세 번째 인자: 권한 목록(ROLE_USER, ROLE_ADMIN 등)
                );
                // 인증 완료 후 SecurityContextHolder에 넣기 -ThreadLocal 기반으로 현재 스레드(요청)에서만 유지되는 SecurityContext에 auth 저장.
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
    }
}
