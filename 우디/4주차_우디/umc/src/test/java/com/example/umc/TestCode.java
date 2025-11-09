//package com.example.umc;
//
//import com.example.umc.domain.member.entity.Member;
//import com.example.umc.domain.member.enums.Gender;
//import com.example.umc.domain.member.enums.MemberStatus;
//import com.example.umc.domain.member.repository.MemberRepository;
//import com.example.umc.domain.store.entity.Store;
//import com.example.umc.domain.store.enums.FoodCategory;
//import com.example.umc.domain.store.enums.StoreStatus;
//import com.example.umc.domain.store.mapping.Food;
//import com.example.umc.domain.store.repository.FoodRepository;
//import com.example.umc.domain.store.repository.StoreRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class TestCode {
//
//    private final MemberRepository memberRepository;
//    private final StoreRepository storeRepository;
//    private final FoodRepository foodRepository;
//
//    @Transactional
//    public void addMember() {
//        Member member1 = Member.builder()
//                .username("닉네임1234")
//                .nickname("닉네임1234")
//                .gender(Gender.MALE)
//                .birth("0000")
//                .address("부산광역시 남구 대연동")
//                .position("남구")
//                .status(MemberStatus.ACTIVE)
//                .point(0)
//                .phoneNumber("0000-0000-0000")
//                .email("aaaa")
//                .successMissionCnt(0)
//                .build();
//
//        Member member2 = Member.builder()
//                .username("닉네임2345")
//                .nickname("닉네임2345")
//                .gender(Gender.MALE)
//                .birth("1111")
//                .address("부산광역시 금정구 장전동")
//                .position("금정구")
//                .status(MemberStatus.ACTIVE)
//                .point(10000)
//                .phoneNumber("0000-0000-1111")
//                .email("bbbb")
//                .successMissionCnt(1)
//                .build();
//
//        memberRepository.save(member1);
//        memberRepository.save(member2);
//    }
//
//    @Transactional
//    public void addStore() {
//        Food food = Food.builder()
//                .food(FoodCategory.CHINESE)
//                .build();
//        foodRepository.save(food);
//
//        Store store = Store.builder()
//                .name("반이학생마라탕마라반")
//                .address("부산광역시 남구 대연동")
//                .position("남구")
//                .rating(4.5)
//                .status(StoreStatus.ACTIVE)
//                .food(food)
//                .build();
//        storeRepository.save(store);
//    }
//
//    @Transactional
//    public void addReview() {
//
//    }
//}
