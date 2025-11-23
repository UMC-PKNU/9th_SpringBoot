package com.example.umc9th_week5.domain.region.service.command;

import com.example.umc9th_week5.domain.region.dto.res.RegionResDTO;

public interface RegionCommandService {
    RegionResDTO.RegionInfo findRegionById(Long id);
}
