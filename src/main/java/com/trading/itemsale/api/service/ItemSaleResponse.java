package com.trading.itemsale.api.service;

import com.trading.item.domain.ItemType;
import com.trading.itemsale.domain.ItemSaleInfo;
import com.trading.itemsale.domain.ItemSaleInfoStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemSaleResponse {

    private Long itemSaleId;
    private String memberName;
    private String itemName;
    private int itemPrice;
    private ItemType itemType;
    private String description;
    private ItemSaleInfoStatus itemSaleInfoStatus;
    private int quantity;

    @Builder
    private ItemSaleResponse(Long itemSaleId, String memberName, String itemName, int itemPrice, ItemType itemType, String description, ItemSaleInfoStatus itemSaleInfoStatus, int quantity) {
        this.itemSaleId = itemSaleId;
        this.memberName = memberName;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.description = description;
        this.itemSaleInfoStatus = itemSaleInfoStatus;
        this.quantity = quantity;
    }

    public static ItemSaleResponse from(ItemSaleInfo itemSaleInfo) {
        return ItemSaleResponse.builder()
                .itemSaleId(itemSaleInfo.getId())
                .memberName(itemSaleInfo.getMemberName())
                .itemName(itemSaleInfo.getItemName())
                .itemPrice(itemSaleInfo.getItemPrice())
                .itemType(itemSaleInfo.getItemType())
                .description(itemSaleInfo.getDescription())
                .quantity(itemSaleInfo.getQuantity())
                .itemSaleInfoStatus(itemSaleInfo.getStatus())
                .build();
    }
}
