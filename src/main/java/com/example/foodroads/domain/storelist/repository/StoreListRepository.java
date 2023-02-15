package com.example.foodroads.domain.storelist.repository;

import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.storelist.entity.StoreList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreListRepository extends JpaRepository<StoreList, Long> {

    Optional<StoreList> findByIdAndMember(Long storeListId, Member member);
}
