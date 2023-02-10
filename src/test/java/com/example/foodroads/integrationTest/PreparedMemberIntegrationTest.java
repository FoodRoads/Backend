package com.example.foodroads.integrationTest;

import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.member.repository.MemberRepository;
import com.example.foodroads.testFixtures.MemberFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PreparedMemberIntegrationTest extends IntegrationTest {
    protected static final String NAME = "MEMBER-NAME";
    protected static final String SOCIAL_ID = "MEMBER-SOCIAL-ID";
    protected static final String SOCIAL_TYPE = "KAKAO";

    @Autowired
    protected MemberRepository memberRepository;

    protected Long memberId;

    protected Member member;

    @BeforeEach
    void setUpMember() {
        member = memberRepository.save(MemberFixture.create(NAME, SOCIAL_ID, SOCIAL_TYPE));
        memberId = member.getId();
    }

    @AfterEach
    protected void cleanup() {
        memberRepository.delete(member);
    }
}
