//package com.example.umc.domain.member.service;
//
//import com.example.umc.domain.member.dto.MemberDto;
//import com.example.umc.domain.member.entity.Member;
//import com.example.umc.domain.member.entity.mapping.MemberMission;
//import com.example.umc.domain.member.repository.MemberRepository;
//import com.example.umc.domain.mission.entity.Mission;
//import com.example.umc.domain.food.entity.Food;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@Transactional
//@Service
//public class MemberService {
//    private final MemberRepository memberRepository;
//
//    // 미션2
//    public MemberDto findById(Long id) {
//        Optional<Member> optionalMember = memberRepository.findById(id);
//
//        Member member = optionalMember.orElseThrow(() ->
//                new EntityNotFoundException("해당 멤버를 찾을 수 없습니다."));
//
//        return MemberDto.from1(member);
//    }
//
//    public Page<MemberDto> f(Long id, Pageable pageable) {
//        Page<Object[]> missionPage = memberRepository.findMissionsByMemberId(id, pageable);
//
//        Page<MemberDto> dtoPage = missionPage.map(data -> {
//
//            Member member = (Member) data[0];
//            MemberMission memberMission = (MemberMission) data[1];
//            Food food = (Food) data[2];
//            Mission mission = (Mission) data[3];
//
//            return MemberDto.from2(member, memberMission, food, mission);
//        });
//
//        return dtoPage;
//    }
//}
