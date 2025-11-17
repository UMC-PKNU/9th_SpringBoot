package com.example.umc.domain.member.service.query;

import com.example.umc.domain.member.converter.MemberConverter;
import com.example.umc.domain.member.dto.res.MemberResDto;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.member.exception.MemberException;
import com.example.umc.domain.member.exception.code.MemberErrorCode;
import com.example.umc.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    @Override
    public void checkFlag(Long flag) {
        if (flag == null)
            return;
        if (flag == 1) {
            throw new MemberException(MemberErrorCode.UNAUTHORIZED);
        } else if (flag == 2) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }
    }

    @Override
    public MemberResDto.GetMyPage getMyPage(Long memberId) {
        Optional<Member> OptionalMember = memberRepository.findById(memberId);

        Member member = OptionalMember.orElseThrow(()
                -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetMyPageDto(member);
    }

    @Override
    public Page<MemberResDto.GetMemberMissions> getMyMissions(Long memberId, MissionStatus status, Pageable pageable) {
        // 1. Repository에서 원본 Page<Object[]>를 가져옵니다.
        Page<Object[]> missionsByMemberId = memberRepository.findMissionsByMemberId(memberId, pageable);

        // 2. 원본 Page에서 내용물(Object[] 리스트)을 꺼냅니다.
        List<Object[]> originalContent = missionsByMemberId.getContent();

        // 3. 변환된 DTO를 담을 새 리스트를 생성합니다.
        List<MemberResDto.GetMemberMissions> dtoList = new ArrayList<>();

        // 4. 'for' 반복문으로 원본 리스트의 아이템을 하나씩 꺼내서 변환합니다.
        for (Object[] result : originalContent) {
            // .map()이 하던 컨버터 호출을 수동으로 합니다.
            MemberResDto.GetMemberMissions dto = MemberConverter.toGetMemberMissionDto(result);

            // 변환된 DTO를 새 리스트에 추가합니다.
            dtoList.add(dto);
        }

        // 5. 변환된 DTO 리스트(dtoList)와 원본의 페이지 정보(pageable, totalElements)를 합쳐
        //    새로운 Page 객체(PageImpl)를 만들어 반환합니다.
        Page<MemberResDto.GetMemberMissions> myMissionDtoPage = new PageImpl<>(
                dtoList,                                // 변환된 내용물
                missionsByMemberId.getPageable(),       // 원본의 페이지 요청 정보
                missionsByMemberId.getTotalElements()   // 원본의 전체 아이템 개수
        );
        return myMissionDtoPage;
    }
}

