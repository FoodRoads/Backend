package com.example.foodroads.domain.member.entity;

import com.example.foodroads.common.entity.BaseTimeEntity;
import com.example.foodroads.domain.storelist.entity.StoreList;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    private String nickname;

    private String email;

    private String phone_number;

    private String socialId;

    private String socialType;

    @OneToMany(mappedBy = "member")
    private final List<StoreList> storeLists = new ArrayList<>();

}
