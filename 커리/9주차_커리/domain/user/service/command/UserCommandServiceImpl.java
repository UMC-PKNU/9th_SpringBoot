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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserMissionMapRepository userMissionMapRepository;

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


}
