package com.example.foodroads.testFixtures;

import com.example.foodroads.domain.member.entity.Member;

public class MemberFixture {
    public static Member create(String name, String socialId, String socialType) {
        return Member.newInstance(name, socialId, socialType, null);
    }
}
