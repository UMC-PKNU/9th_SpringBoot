package com.example.umc9th_week5.global.validation;

import com.example.umc9th_week5.domain.member.code.MemberErrorCode;
import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.member.repository.MemberRepository;
import com.example.umc9th_week5.domain.mission.code.MissionErrorCode;
import com.example.umc9th_week5.domain.mission.code.MemberMissionErrorCode;
import com.example.umc9th_week5.domain.mission.entity.MemberMission;
import com.example.umc9th_week5.domain.mission.entity.Mission;
import com.example.umc9th_week5.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th_week5.domain.mission.repository.MissionRepository;
import com.example.umc9th_week5.domain.region.code.RegionErrorCode;
import com.example.umc9th_week5.domain.region.entity.Region;
import com.example.umc9th_week5.domain.region.repository.RegionRepository;
import com.example.umc9th_week5.domain.store.code.StoreErrorCode;
import com.example.umc9th_week5.domain.store.entity.Store;
import com.example.umc9th_week5.domain.store.repository.StoreRepository;
import com.example.umc9th_week5.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th_week5.global.apiPayload.exception.GeneralException;
import com.example.umc9th_week5.global.entity.FoodCategory;
import com.example.umc9th_week5.global.repository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GlobalValidatorService {
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final FoodCategoryRepository foodCategoryRepository;


    public Member validateExistMemberById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(() -> new GeneralException(MemberErrorCode.NOT_FOUND));
    }

    public Store validateExistStoreById(Long storeId){
        return storeRepository.findById(storeId).orElseThrow(() -> new GeneralException(StoreErrorCode.NOT_FOUND));
    }

    public Region validateExistRegionById(Long regionId){
        return regionRepository.findById(regionId).orElseThrow(() -> new GeneralException(RegionErrorCode.NOT_FOUND));
    }

    public Mission validateExistMissionById(Long missionId){
        return missionRepository.findById(missionId).orElseThrow(() -> new GeneralException(MissionErrorCode.NOT_FOUND));
    }

    public MemberMission validateExistMemberMissionByMemberIdAndMissionId(Long memberId, Long missionId){
        return memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId).orElseThrow(() -> new GeneralException(MemberMissionErrorCode.NOT_FOUND));
    }

    public FoodCategory validateExistFoodCategoryById(Long foodCategoryId){
        return foodCategoryRepository.findById(foodCategoryId).orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
    }


}
