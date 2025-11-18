package com.example.umc_9th_spring.domain.user.service.command;

import com.example.umc_9th_spring.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;

public interface UserCommandService {
    UserResDTO.UserInfo userSignUp(UserReqDTO.SignUp signupInfo);
}
