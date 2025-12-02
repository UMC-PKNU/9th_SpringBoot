package com.example.umc_9th_.domain.user.service.command;

import com.example.umc_9th_.domain.user.dto.UserRequest;
import com.example.umc_9th_.domain.user.dto.UserResponse;
import com.example.umc_9th_.domain.user.entity.User;

public interface UserCommandService {
    UserResponse.JoinResultDTO joinUser(UserRequest.JoinDTO request);
    UserResponse.LocationUpdateDTO updateLocation(Long userId, UserRequest.UpdateLocationDTO request);
}