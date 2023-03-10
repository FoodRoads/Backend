package com.example.foodroads.integrationTest.auth;

import com.example.foodroads.client.provider.AuthProvider;
import com.example.foodroads.domain.auth.entity.RefreshToken;
import com.example.foodroads.domain.auth.repository.RefreshTokenRepository;
import com.example.foodroads.domain.auth.service.AuthProviderFinder;
import com.example.foodroads.domain.auth.service.AuthService;
import com.example.foodroads.domain.auth.service.AuthServiceImpl;
import com.example.foodroads.domain.auth.service.JwtTokenProvider;
import com.example.foodroads.domain.auth.service.dto.LoginRequest;
import com.example.foodroads.domain.auth.service.dto.LoginResponse;
import com.example.foodroads.domain.auth.service.dto.SignUpRequest;
import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.member.repository.MemberRepository;
import com.example.foodroads.integrationTest.IntegrationTest;
import com.example.foodroads.testFixtures.MemberFixture;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AuthServiceTest extends IntegrationTest {

    private static final String AUTH_TOKEN = "test-social-access-token";
    private static final String SOCIAL_ID = "test-social-id";
    private static final String SOCIAL_TYPE = "KAKAO";

    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    void setupAuthService() {
        AuthProviderFinder authProviderFinder = socialType ->  new StubAuthProvider();
        authService = new AuthServiceImpl(
                authProviderFinder,
                jwtTokenProvider,
                memberRepository,
                refreshTokenRepository
        );
    }

    @AfterEach
    void cleanUpRefreshToken() {
        refreshTokenRepository.deleteAll();
        memberRepository.deleteAll();
    }

    private static class StubAuthProvider implements AuthProvider {

        @Override
        public String getSocialId(@NotNull String token) {
            return SOCIAL_ID;
        }

    }

    @Nested
    class LoginTest {

        @Test
        void ????????????_????????????_?????????_accessToken???_refreshToken???_????????????() {
            // given
            Member member = MemberFixture.create("FoodRoads", SOCIAL_ID, SOCIAL_TYPE);
            memberRepository.save(member);

            LoginRequest request = LoginRequest.builder()
                    .token(AUTH_TOKEN)
                    .socialType(SOCIAL_TYPE)
                    .build();

            // when
            LoginResponse response = authService.login(request);
            List<RefreshToken> refreshTokens = refreshTokenRepository.findAll();
            // then
            assertAll(
                    () -> assertThat(jwtTokenProvider.getMemberId(response.getAccessToken())).isEqualTo(member.getId()),
                    () -> assertThat(refreshTokens).hasSize(1),
                    () -> assertThat(jwtTokenProvider.validateToken(response.getRefreshToken())).isTrue()
            );
        }
    }

    @Nested
    class SignupTest {

        @Test
        void ???????????????_????????????_?????????_?????????_????????????() {
            // given
            SignUpRequest request = SignUpRequest.builder()
                    .token(AUTH_TOKEN)
                    .name("FoodRoads")
                    .socialType(SOCIAL_TYPE)
                    .build();

            // when
            authService.signUp(request);

            // then
            List<Member> members = memberRepository.findAll();
            assertAll(
                    () -> assertThat(members).hasSize(1),
                    () -> assertThat(members.get(0).getName()).isEqualTo(request.getName()),
                    () -> assertThat(members.get(0).getSocialId()).isEqualTo(SOCIAL_ID),
                    () -> assertThat(members.get(0).getSocialType()).isEqualTo(request.getSocialType())
            );
        }
    }
}
