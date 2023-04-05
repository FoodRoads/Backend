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

//    @Query("select distinct sl from StoreList sl join StoreListDetail sld on sl = sld.storeList " +
//            "where sl.name like %:keyword% " +
//            "or sl.member.name like %:keyword% " +
//            "or sld.store.name like %:keyword% " +
//            "or sld.description like %:keyword%")
    @Query(value = "SELECT sl.id, sl.member_id, sl.name\n" +
            "FROM store_list sl\n" +
            "WHERE MATCH(sl.name) AGAINST(?1 in boolean mode)\n" +
            "UNION\n" +
            "SELECT sl.id, sl.member_id, sl.name\n" +
            "FROM store_list sl\n" +
            "WHERE sl.member_id IN (\n" +
            "    SELECT m.id\n" +
            "    FROM member m\n" +
            "    WHERE MATCH(m.name) AGAINST(?1 in boolean mode)\n" +
            ")\n" +
            "UNION\n" +
            "SELECT sl.id, sl.member_id, sl.name\n" +
            "FROM store_list sl\n" +
            "WHERE sl.id IN (\n" +
            "    SELECT sld.store_list_id\n" +
            "    FROM store_list_detail sld\n" +
            "    WHERE MATCH(sld.description) AGAINST(?1 in boolean mode)\n" +
            "    UNION\n" +
            "    select sld.store_list_id\n" +
            "    FROM store_list_detail sld\n" +
            "             JOIN store s ON s.id = sld.store_id\n" +
            "    WHERE MATCH(s.name) AGAINST(?1 in boolean mode)\n" +
            ")", nativeQuery = true)
    List<StoreList> searchByKeyword(@Param("keyword") String keyword);
}
