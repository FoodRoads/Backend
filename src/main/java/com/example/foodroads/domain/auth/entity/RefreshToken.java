package com.example.foodroads.domain.auth.entity;

import com.example.foodroads.domain.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RefreshToken {
    @Id
    private Long memberId;
    private String refreshToken;

    public static RefreshToken of(Member member, String refreshToken) {
        return new RefreshToken(member.getId(), refreshToken);
    }
}