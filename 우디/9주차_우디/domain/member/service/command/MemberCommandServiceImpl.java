package com.example.umc.domain.member.service.command;

import com.example.umc.domain.food.exception.FoodException;
import com.example.umc.domain.food.exception.code.FoodErrorCode;
import com.example.umc.domain.member.converter.MemberConverter;
import com.example.umc.domain.member.dto.req.MemberReqDto;
import com.example.umc.domain.member.dto.res.MemberResDto;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.entity.mapping.MemberFood;
import com.example.umc.domain.member.repository.MemberFoodRepository;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.food.entity.Food;
import com.example.umc.domain.food.repository.FoodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    // 회원가입
    @Override
    @Transactional
    public MemberResDto.JoinDto signup(MemberReqDto.JoinDto dto) {

        // 클라이언트에서 받아온 DTO를 엔티티로 변환한다.
        // 레파지토리에서 엔티티를 찾아오기 위해서?
        Member member = MemberConverter.toMember(dto);
        memberRepository.save(member);

        if (dto.preferFoodIds().size() > 1) {
            List<MemberFood> memberFoodList = new ArrayList<>();

            for (Long foodId: dto.preferFoodIds()) {
                Food food = foodRepository.findById(Math.toIntExact(foodId))
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                memberFoodList.add(memberFood);
            }

            memberFoodRepository.saveAll(memberFoodList);
        }

        return MemberConverter.toJoinDto(member);
    }
}
