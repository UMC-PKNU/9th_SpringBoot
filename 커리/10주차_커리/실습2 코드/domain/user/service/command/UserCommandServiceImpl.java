package com.example.umc_9th_spring.domain.user.service.command;

import com.example.umc_9th_spring.domain.mission.code.MissionErrorCode;
import com.example.umc_9th_spring.domain.mission.converter.MissionConverter;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.mission.exception.MissionException;
import com.example.umc_9th_spring.domain.user.code.UserErrorCode;
import com.example.umc_9th_spring.domain.user.converter.UserConverter;
import com.example.umc_9th_spring.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_spring.domain.user.entity.User;
import com.example.umc_9th_spring.domain.user.entity.UserMissionMap;
import com.example.umc_9th_spring.domain.user.exception.UserException;
import com.example.umc_9th_spring.domain.user.repository.UserMissionMapRepository;
import com.example.umc_9th_spring.domain.user.repository.UserRepository;
import com.example.umc_9th_spring.global.auth.details.CustomUserDetails;
import com.example.umc_9th_spring.global.auth.enums.Role;
import com.example.umc_9th_spring.global.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserMissionMapRepository userMissionMapRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResDTO.UserInfo userSignUp(UserReqDTO.SignUp dto){
        if(dto == null){
            throw new UserException(UserErrorCode.USER_INVALID_MISSION_STATUS);
        }

        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new UserException(UserErrorCode.USER_DUPLICATED_EMAIL);
        }
        String salt = passwordEncoder.encode(dto.getPassword());

        User user = UserConverter.toEntity(dto, salt, Role.ROLE_USER);
        //User user = User.createSignUpUser(signupInfo);

        User savedUser = userRepository.save(user);

        return UserConverter.toUserInfoDTO(savedUser);
    }

    @Override
    public MissionResDTO.UserMissionInfo completeMission(Long userId, Long userMissionId) {

        if (!userRepository.existsById(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        UserMissionMap map = userMissionMapRepository.findByIdAndUser_Id(userMissionId, userId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_RECEIVED));

        map.complete();

        return MissionConverter.toUserMissionInfo(map);
    }

    @Override
    public UserResDTO.LoginResult login(UserReqDTO.@Valid Login dto){

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())){
            throw new UserException(UserErrorCode.USER_INVALID_PWD);
        }

        // JWT 토큰 발급용 UserDetails
        /*
        User 엔티티를 바로 Authentication 인증 객체로 쓰지 않고
        UserDetails 로 감싸는 이유는 Security 의 표준 인증 구조를 맞추기 위함임.
         */
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        return UserConverter.toLoginDTO(user, accessToken);

    }

}
