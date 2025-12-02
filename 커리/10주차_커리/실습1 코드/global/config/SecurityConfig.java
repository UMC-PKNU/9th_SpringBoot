package com.example.umc_9th_spring.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity //Spring Security 설정 활성화
@Configuration
public class SecurityConfig { //필터 체인과 보안 정책을 설정하는 역할
    private final String[] allowUris = {
            "/users",

            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    //우리가 직접 작성한 보안 설정이 Spring Security의 기본 설정보다 우선 적용되게 됩.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Swagger에서 POST 막히지 않게
                //HTTP 요청에 대한 접근 제어를 설정
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()  // 회원가입 허용
                        .requestMatchers(allowUris).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                //폼 기반 로그인에 대한 설정
                .formLogin(login -> login
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )
                //로그아웃 처리에 대한 설정
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder  passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    @Bean
    public JwtAuthFiler jwtAuthFiler(){
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }
    */

}
