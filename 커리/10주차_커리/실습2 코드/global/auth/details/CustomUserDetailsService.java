package com.example.umc_9th_spring.global.auth.details;

import com.example.umc_9th_spring.domain.user.code.UserErrorCode;
import com.example.umc_9th_spring.domain.user.entity.User;
import com.example.umc_9th_spring.domain.user.exception.UserException;
import com.example.umc_9th_spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //검증할 User 조회
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return new CustomUserDetails(user);
    }

    /*

    여기서 `CustomUserDetailService` 는 `loadByUsername` 메소드로 `AuthenticationManager` 의 호출을 받아
    DB에서 `findByEmail` 메소드로 사용자 정보를 조회함.

    조회된 `User` 엔티티를 기반으로 Spring Security가 요구하는 `UserDetails` 객체를 반환함.

     */
}
