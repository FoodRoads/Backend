package com.example.foodroads.domain.storelist.service.dto;

import com.example.foodroads.domain.member.service.dto.MemberInfoResponse;
import com.example.foodroads.domain.storelist.entity.StoreList;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreListResponse {

    private long storeListId;

    private String storeListName;

    private MemberInfoResponse memberInfo;

    public static StoreListResponse of(StoreList storeList) {
        return new StoreListResponse(storeList.getId(), storeList.getName(), MemberInfoResponse.of(storeList.getMember()));
    }
}
