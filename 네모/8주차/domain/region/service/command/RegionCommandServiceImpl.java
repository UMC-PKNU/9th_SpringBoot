package com.example.umc9th_week5.domain.region.service.command;

import com.example.umc9th_week5.domain.region.code.RegionErrorCode;
import com.example.umc9th_week5.domain.region.converter.RegionConverter;
import com.example.umc9th_week5.domain.region.dto.res.RegionResDTO;
import com.example.umc9th_week5.domain.region.entity.Region;
import com.example.umc9th_week5.domain.region.repository.RegionRepository;
import com.example.umc9th_week5.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegionCommandServiceImpl implements RegionCommandService{
    private final RegionRepository regionRepository;

    @Override
    public RegionResDTO.RegionInfo findRegionById(Long id){
        Region region = regionRepository.findById(id).orElseThrow(() -> new GeneralException(RegionErrorCode.NOT_FOUND));
        return RegionConverter.toRegionResDTO(region);
    }
}
