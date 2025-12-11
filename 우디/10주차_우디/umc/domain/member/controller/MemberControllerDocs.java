package com.example.umc.domain.member.controller;

import com.example.umc.domain.member.dto.res.MemberMissionResDto;
import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.review.dto.res.ReviewResDto;
import com.example.umc.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberControllerDocs {

    @Operation(
            summary = "리뷰 목록 조회 API By 우디 (개발 완료)",
            description = "회원이 작성한 리뷰를 모두 조회합니다. 페이지네이션을 제공합니다.\n\n" +
                    "특정 가게에서 작성한 회원의 리뷰 또한 조회할 수 있습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDto.ReviewPreViewListDto> getReviews(@PathVariable Long memberId,
                                                              @RequestParam(required = false) String storeName,
                                                              @RequestParam Integer page);

    @Operation(
            summary = "회원 미션 목록 조회 API By 우디 (개발 완료)",
            description = "status = IN_PROGRESS를 받아서 진행 중인 미션 목록을 모두 조회합니다. 페이지네이션을 제공합니다. \n\n" +
                    "실패 또는 성공한 미션도 조회할 수 있습니다.\n\n" +
                    "- 실패: status = FAIL \n\n" +
                    "- 성공: status = SUCCESS"
    )
    ApiResponse<MemberMissionResDto.GetMemberMissionsDto> getMyMissions(@PathVariable Long memberId,
                                                                        @RequestParam MissionStatus status,
                                                                        @RequestParam Integer page);
}
