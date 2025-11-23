package com.example.umc.domain.mission.service;

import com.example.umc.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class MissionService {

    private final MissionRepository missionRepository;


}

