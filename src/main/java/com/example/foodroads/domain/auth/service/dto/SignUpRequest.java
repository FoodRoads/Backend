package com.example.foodroads.domain.auth.service.dto;

import com.example.foodroads.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

    @NotBlank
    private String token;

    @NotBlank
    private String name;

    @NotNull
    private String socialType;

    private String profileImgUrl;

    @Builder
    private SignUpRequest(String token, String name, String socialType, String profileImgUrl) {
        this.token = token;
        this.name = name;
        this.socialType = socialType;
        this.profileImgUrl = profileImgUrl;
    }

    public Member toEntity(String socialId) {
        return Member.newInstance(name, socialId, socialType, profileImgUrl);
    }
}
