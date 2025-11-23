package com.example.umc9th_week5.domain.region.converter;

import com.example.umc9th_week5.domain.region.dto.req.RegionReqDTO;
import com.example.umc9th_week5.domain.region.dto.res.RegionResDTO;
import com.example.umc9th_week5.domain.region.entity.Region;

public class RegionConverter {
    //entity -> resDto
    public static RegionResDTO.RegionInfo toRegionResDTO(Region region){
        return RegionResDTO.RegionInfo.builder()
                .id(region.getId())
                .regionName(region.getRegionName())
                .build();
    }

    //entity -> reqDto
    public static RegionReqDTO.regionReqDTO toRegionReqDTO(Region region){
        return RegionReqDTO.regionReqDTO.builder()
                .id(region.getId())
                .regionName(region.getRegionName())
                .build();
    }


    //dto -> entity
    public static Region toRegionEntity(RegionReqDTO.regionReqDTO dto){
        return Region.builder()
                .regionName(dto.regionName())
                .build();
    }

}
