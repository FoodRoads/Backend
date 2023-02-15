package com.example.foodroads.domain.storelist.service.dto;

import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.storelist.entity.StoreList;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreListUpdateRequest {
    @NotBlank
    private String name;

    @Builder
    private StoreListUpdateRequest(String name) {
        this.name = name;
    }
}
