package com.example.foodroads.domain.storelist.service.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreListDetailUpdateRequest {

    private String description;

    @Builder
    private StoreListDetailUpdateRequest(String description) {
        this.description = description;
    }
}
