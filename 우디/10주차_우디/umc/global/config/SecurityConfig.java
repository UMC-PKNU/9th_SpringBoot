package com.example.umc.global.config;

// 애플리케이션의 보안 정책을 정의하는 곳
// 어떤 URL은 누구나 접근가능하고
// 다른 URL은 관리자만 접근가능하도록 지정하는 곳

import com.example.umc.global.auth.principal.AuthenticationEntryPointImpl;
import com.example.umc.global.auth.principal.CustomUserDetailsService;
import com.example.umc.global.auth.principal.JwtAuthFilter;
import com.example.umc.global.auth.principal.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity   // Spring Security 설정을 활성화시키는 역할
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    private final String[] allowUris = {
            "/members/login",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    // SecurityFilterChain을 정의하는 메서드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // HTTP 요청에 대한 접근 제어 설정
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(allowUris).permitAll()   // 특정 URL 패턴에 대한 접근 권한 설정
//                .requestMatchers("/swagger-ui/index.html").hasRole("ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        )
                // 폼 로그인 비활성화
//                .formLogin(AbstractHttpConfigurer::disable)
//                 폼 로그인 활성화
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )

                // JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
//                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)   //
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()   // 로그아웃에 대한 요청은 인증과 인가를 따지지 않고 모두 허용
                );
//                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint()));

        return http.build();
    }

    // 비밀번호 솔트를 위한 메서드
    // sort (x) salt (o)
    // 같은 비밀번호라도 랜덤한 문자열을 붙여 암호화해서 DB에는 다르게 저장된다.
    // 랜덤한 문자열을 붙이는거를 소금을 친다라고 생각하면 될듯
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }
}
