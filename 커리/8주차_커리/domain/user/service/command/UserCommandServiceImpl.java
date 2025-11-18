package com.example.umc_9th_spring.domain.user.service.command;

import com.example.umc_9th_spring.domain.user.code.UserErrorCode;
import com.example.umc_9th_spring.domain.user.converter.UserConverter;
import com.example.umc_9th_spring.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_spring.domain.user.entity.User;
import com.example.umc_9th_spring.domain.user.exception.UserException;
import com.example.umc_9th_spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    @Override
    public UserResDTO.UserInfo userSignUp(UserReqDTO.SignUp signupInfo){
        if(signupInfo == null){
            throw new UserException(UserErrorCode.USER_INVALID_MISSION_STATUS);
        }

        if(userRepository.findByEmail(signupInfo.getEmail()).isPresent()){
            throw new UserException(UserErrorCode.USER_DUPLICATED_EMAIL);
        }

        User user = User.createSignUpUser(signupInfo);

        User savedUser = userRepository.save(user);

        return UserConverter.toUserInfoDTO(savedUser);
    }

}
