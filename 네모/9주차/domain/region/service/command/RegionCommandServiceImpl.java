package com.example.umc9th_week5.domain.region.service.command;

import com.example.umc9th_week5.domain.region.converter.RegionConverter;
import com.example.umc9th_week5.domain.region.dto.res.RegionResDTO;
import com.example.umc9th_week5.domain.region.entity.Region;
import com.example.umc9th_week5.global.validation.GlobalValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionCommandServiceImpl implements RegionCommandService {
    private final GlobalValidatorService globalValidatorService;

    @Override
    public RegionResDTO.RegionInfo findRegionById(Long id) {
        Region region = globalValidatorService.validateExistRegionById(id);
        return RegionConverter.toRegionResDTO(region);
    }
}