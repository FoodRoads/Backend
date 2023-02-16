package com.example.foodroads.domain.member.service.dto;

import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.storelist.entity.StoreListDetail;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberInfoResponse {

    private Long memberId;

    private String memberName;

    private String memberProfileImgUrl;

    public static MemberInfoResponse of(Member member) {
        return new MemberInfoResponse(member.getId(), member.getName(), member.getProfileImgUrl());
    }

}
