package com.example.umc9th_week5.domain.member.service.command;

import com.example.umc9th_week5.domain.member.converter.MemberConverter;
import com.example.umc9th_week5.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.member.entity.MemberFoodCategory;
import com.example.umc9th_week5.domain.member.repository.MemberFoodCategoryRepository;
import com.example.umc9th_week5.domain.member.repository.MemberRepository;
import com.example.umc9th_week5.global.entity.FoodCategory;
import com.example.umc9th_week5.global.enums.Role;
import com.example.umc9th_week5.global.validator.GlobalValidatorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MemberFoodCategoryRepository memberFoodCategoryRepository;
    private final GlobalValidatorService globalValidatorService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public MemberResDTO.MemberInfo signup(MemberReqDTO.JoinDTO dto) {
        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMemberEntityForSignUp(dto, salt, Role.ROLE_USER);
        memberRepository.save(member);

        //선호 음식 존재 여부
        //선호 음식을 for문으로 돌려서 하나씩 조회, MemberFood 생성 후 List<MemberFood> 에 저장
        //해당 List를 saveAll로 DB에 저장
        if (dto.preferCategory().size() > 1) {
            List<MemberFoodCategory> memberFoodList = new ArrayList<>();

            for (Long id : dto.preferCategory()) {
                FoodCategory foodCategory = globalValidatorService.validateExistFoodCategoryById(id);

                MemberFoodCategory memberFoodCategory = MemberFoodCategory.builder().member(member).foodCategory(foodCategory).build();

                memberFoodList.add(memberFoodCategory);
            }

            memberFoodCategoryRepository.saveAll(memberFoodList);
        }

        /*
        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1) {
            List<MemberFood> memberFood = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());

            memberFoodRepository.saveAll(memberFood);
        }
        */

        return MemberConverter.toMemberDTO(member);
    }
}
