package com.example.foodroads.domain.storelist.repository;

import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.storelist.entity.StoreList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreListRepository extends JpaRepository<StoreList, Long> {

    Optional<StoreList> findByIdAndMember(Long storeListId, Member member);

    @Query("select sl from StoreList sl join StoreListDetail sld where sl.name like %:keyword% or  sld.description like %:keyword%")
    List<StoreList> searchByKeyword(@Param("keyword") String keyword);
}
