package com.example.foodroads.config.interceptor;

import com.example.foodroads.domain.auth.service.JwtTokenProvider;
import com.example.foodroads.domain.member.repository.MemberRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class LoginOptionalCheckHandler {

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberRepository memberRepository;

    Long getMemberIdOptional(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authorization)) {
            return null;
        }

        Long memberId = jwtTokenProvider.getMemberId(authorization);

        if (!isValidUserId(memberId)) {
            return null;
        }
        return memberId;
    }

    private boolean isValidUserId(Long memberId) {
        return memberId != null && memberRepository.existsMemberById(memberId);
    }

}
