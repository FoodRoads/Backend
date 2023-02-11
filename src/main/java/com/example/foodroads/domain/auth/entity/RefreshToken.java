package com.example.foodroads.domain.auth.entity;

import com.example.foodroads.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "refreshToken")
public class RefreshToken {
    @Id
    private Long memberId;

    private String refreshToken;

    @TimeToLive
    private Long expiredTime;

    public static RefreshToken of(Member member, String refreshToken, Long expiredTime) {
        return new RefreshToken(member.getId(), refreshToken, expiredTime);
    }
}