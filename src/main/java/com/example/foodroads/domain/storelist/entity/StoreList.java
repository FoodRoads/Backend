package com.example.foodroads.domain.storelist.entity;

import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
}
