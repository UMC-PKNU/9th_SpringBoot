package com.example.umc_9th_.global.config.security.jwt;

import com.example.umc_9th_.global.config.security.CustomUserDetailsService;
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

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        // 토큰이 유효한지 검사
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");

            if (jwtUtil.isValid(token)) {
                String email = jwtUtil.getEmail(token);

                // 유저 정보 조회 및 인증 객체 생성
                UserDetails user = customUserDetailsService.loadUserByUsername(email);
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

                // SecurityContext에 저장 (로그인 성공 취급)
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}