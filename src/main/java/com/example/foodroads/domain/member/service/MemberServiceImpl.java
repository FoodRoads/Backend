package com.example.foodroads.domain.member.service;

import com.example.foodroads.common.exception.model.NotFoundException;
import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.member.repository.MemberRepository;
import com.example.foodroads.domain.member.service.dto.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.foodroads.common.exception.ErrorCode.E404_NOT_EXISTS_USER;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberInfoResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException("존재하지 않는 유저 입니다", E404_NOT_EXISTS_USER)
        );

        return MemberInfoResponse.of(member);
    }
}
