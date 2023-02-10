package com.example.foodroads.domain.auth.service;

import com.example.foodroads.client.provider.AuthProvider;
import com.example.foodroads.common.exception.model.ConflictException;
import com.example.foodroads.common.exception.model.NotFoundException;
import com.example.foodroads.domain.auth.service.dto.LoginRequest;
import com.example.foodroads.domain.auth.service.dto.LoginResponse;
import com.example.foodroads.domain.auth.service.dto.SignUpRequest;
import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.member.repository.MemberRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.foodroads.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthProviderFinder authProviderFinder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public LoginResponse signUp(SignUpRequest request) {
        String socialId = getSocialId(request.getSocialType(), request.getToken());

        if (memberRepository.existsMemberBySocialIdAndSocialType(socialId, request.getSocialType())) {
            throw new ConflictException(String.format("이미 가입한 유저(%s - %s) 입니다", socialId, request.getSocialType()), E409_DUPLICATE_USER);
        }

        if (memberRepository.existsMemberByName(request.getName())) {
            throw new ConflictException(String.format("이미 등록된 닉네임 (%s) 입니다", request.getName()), E409_DUPLICATE_NICKNAME);
        }

        Member member = memberRepository.save(request.toEntity(socialId));

        String accessToken = jwtTokenProvider.createAccessToken(member);
        String refreshToken = jwtTokenProvider.createRefreshToken(member);

        return LoginResponse.of(accessToken, refreshToken);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        String socialId = getSocialId(request.getSocialType(), request.getToken());
        Member member = memberRepository.findBySocialIdAndSocialType(socialId, request.getSocialType()).orElseThrow(
                () -> new NotFoundException(String.format("존재하지 않는 유저 (%s-%s) 입니다", socialId, request.getSocialType()), E404_NOT_EXISTS_USER)
        );

        String accessToken = jwtTokenProvider.createAccessToken(member);
        String refreshToken = jwtTokenProvider.createRefreshToken(member);

        return LoginResponse.of(accessToken, refreshToken);
    }

    private String getSocialId(@NotNull String socialType, @NotNull String token) {

        AuthProvider authProvider = authProviderFinder.findAuthProvider(socialType);
        return authProvider.getSocialId(token);
    }
}
