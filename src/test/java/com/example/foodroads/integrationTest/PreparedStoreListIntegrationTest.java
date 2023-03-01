package com.example.foodroads.integrationTest;

import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.member.repository.MemberRepository;
import com.example.foodroads.domain.storelist.entity.StoreList;
import com.example.foodroads.domain.storelist.repository.StoreListRepository;
import com.example.foodroads.testFixtures.MemberFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PreparedStoreListIntegrationTest extends PreparedMemberIntegrationTest {
    protected static final String NAME = "STORE-LIST-NAME";

    @Autowired
    private StoreListRepository storeListRepository;

    protected Long storeListId;

    protected StoreList storeList;

    @BeforeEach
    void setUpStoreList() {
        storeList = storeListRepository.save(StoreList.newInstance(member, NAME));
        storeListId = storeList.getId();
    }

    @AfterEach
    protected void cleanUpStoreList() {
        storeListRepository.delete(storeList);
    }
}
