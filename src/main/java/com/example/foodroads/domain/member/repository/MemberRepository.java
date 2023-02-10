package com.example.foodroads.domain.member.repository;

import com.example.foodroads.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findBySocialIdAndSocialType(String socialId, String socialType);

    boolean existsMemberBySocialIdAndSocialType(String socialId, String socialType);

    boolean existsMemberByName(String name);

    boolean existsMemberById(Long memberId);
}
