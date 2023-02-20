package com.example.foodroads.domain.member.controller;

import com.example.foodroads.common.dto.ApiResponse;
import com.example.foodroads.config.interceptor.Auth;
import com.example.foodroads.config.resolver.MemberId;
import com.example.foodroads.domain.member.service.MemberService;
import com.example.foodroads.domain.member.service.dto.MemberInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 {memberId}를 조회합니다.")
    @GetMapping("/members/{memberId}")
    ApiResponse<MemberInfoResponse> getMemberInfo(@PathVariable Long memberId) {
        return ApiResponse.success(memberService.getMember(memberId));
    }

    @Operation(summary = "[인증 헤더 필요]나의 정보를 조회합니다.")
    @Auth
    @GetMapping("/members/me")
    ApiResponse<MemberInfoResponse> getMyInfo(@MemberId Long memberId) {
        return ApiResponse.success(memberService.getMember(memberId));
    }
}
