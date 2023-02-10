package com.example.foodroads.config.interceptor;

import com.example.foodroads.common.exception.model.UnAuthorizedException;
import com.example.foodroads.domain.auth.service.JwtTokenProvider;
import com.example.foodroads.domain.member.repository.MemberRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginCheckHandler {

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberRepository memberRepository;

    Long getMemberId(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authorization)) {
            throw new UnAuthorizedException("인증이 실패하였습니다 - Authorization 헤더가 존재하지 않습니다");
        }

        Long memberId = jwtTokenProvider.getMemberId(authorization);

        if (!isValidUserId(memberId)) {
            throw new UnAuthorizedException(String.format("인증이 실패하였습니다 - 토큰(%s)에 해당하는 유저는 회원 탈퇴되거나 유효하지 않은 유저 (%s) 입니다 ", memberId, authorization));
        }
        return memberId;
    }
    private boolean isValidUserId(Long memberId) {
        return memberId != null && memberRepository.existsMemberById(memberId);
    }

}
