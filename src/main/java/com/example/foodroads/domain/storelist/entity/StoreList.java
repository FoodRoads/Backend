package com.example.foodroads.domain.storelist.entity;

import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;

    @OneToMany(mappedBy = "storeList")
    private final List<StoreListDetail> stores = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    private StoreList(Member member, String name) {
        this.member = member;
        this.name = name;
    }

    public static StoreList newInstance(Member member, String name) {
        return StoreList.builder()
                .member(member)
                .name(name)
                .build();
    }
}
