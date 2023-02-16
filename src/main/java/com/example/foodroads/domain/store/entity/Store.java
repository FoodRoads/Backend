package com.example.foodroads.domain.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    private String id;

    private String name;

    private String x;

    private String y;

    private String url;

    @Builder(access = AccessLevel.PACKAGE)
    private Store(String id, String name, String x, String y, String url) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.url = url;
    }

    public static Store newInstance(String id, String name, String x, String y, String url) {
        return Store.builder()
                .id(id)
                .name(name)
                .x(x)
                .y(y)
                .url(url)
                .build();
    }

    public void updateStore(String name, String x, String y, String url) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.url = url;
    }
}
