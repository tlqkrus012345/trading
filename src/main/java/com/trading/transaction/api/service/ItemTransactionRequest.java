package com.trading.transaction.api.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemTransactionRequest {

    private Long itemId;
    private Long memberId;
    private Long inventoryId;
    private int quantity;
    private int itemPrice;

    @Builder
    private ItemTransactionRequest(Long itemId, Long memberId, Long inventoryId, int quantity, int itemPrice) {
        this.itemId = itemId;
        this.memberId = memberId;
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }
}
