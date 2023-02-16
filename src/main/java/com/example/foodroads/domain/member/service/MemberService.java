package com.example.foodroads.domain.member.service;

import com.example.foodroads.domain.member.service.dto.MemberInfoResponse;

public interface MemberService {
    MemberInfoResponse getMember(Long memberId);
}
