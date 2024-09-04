package com.trading.search.api.service;

import com.trading.item.domain.Item;
import com.trading.item.domain.ItemType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemSearchResponse {

    private String name;
    private ItemType type;
    private int price;
    private String description;

    @Builder
    private ItemSearchResponse(String name, ItemType type, int price, String description) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public static ItemSearchResponse from(Item item) {
        return ItemSearchResponse.builder()
                .name(item.getName())
                .price(item.getPrice())
                .type(item.getType())
                .description(item.getDescription())
                .build();
    }
}
