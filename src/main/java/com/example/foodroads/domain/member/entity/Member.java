package com.example.foodroads.domain.member.entity;

import com.example.foodroads.common.entity.BaseTimeEntity;
import com.example.foodroads.domain.storelist.entity.StoreList;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String socialId;

    private String socialType;

    @OneToMany(mappedBy = "member")
    private final List<StoreList> storeLists = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    private Member(String name, String socialId, String socialType) {
        this.name = name;
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public static Member newInstance(String name, String socialId, String socialType) {
        return Member.builder()
                .socialId(socialId)
                .socialType(socialType)
                .name(name)
                .build();
    }
}
