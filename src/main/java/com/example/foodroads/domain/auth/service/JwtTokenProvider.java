package com.example.foodroads.domain.auth.service;

import com.example.foodroads.domain.auth.entity.RefreshToken;
import com.example.foodroads.domain.auth.repository.RefreshTokenRepository;
import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.member.repository.MemberRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY;
    private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60;		// 1hour
    private final Long REFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 7;	// 1week

    private final MemberRepository memberRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public JwtTokenProvider(@Value("${app.auth.token.secret-key}") String secretKey,
                            MemberRepository memberRepository, RefreshTokenRepository refreshTokenRepository) {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.memberRepository = memberRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createAccessToken(Member member) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setSubject(member.getId().toString())
                .setIssuer("foodroads")
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();
    }

    public String createRefreshToken(Member member) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_LENGTH);

        String refreshToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setIssuer("foodroads")
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();

        refreshTokenRepository.save(RefreshToken.of(member, refreshToken));

        return refreshToken;
    }

    public Long getMemberId(String accessToken) {
        Claims claims = parseClaims(accessToken);

        return Long.valueOf(claims.getSubject());
    }


    public Boolean validateToken(String token) {

        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | IllegalStateException e) {
            return false;
            //throw new UnAuthorizedException(String.format("유효하지 않는 토큰(%s) 입니다", token));
        }
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
