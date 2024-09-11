package com.trading.transaction.api.service;

import com.trading.transaction.domain.ItemTransaction;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemTransactionResponse {

    private Long itemTransactionId;
    private LocalDateTime orderedAt;
    private int totalPrice;

    @Builder
    private ItemTransactionResponse(Long itemTransactionId, LocalDateTime orderedAt, int totalPrice) {
        this.itemTransactionId = itemTransactionId;
        this.orderedAt = orderedAt;
        this.totalPrice = totalPrice;
    }

    public static ItemTransactionResponse from(ItemTransaction itemTransaction) {
        return ItemTransactionResponse.builder()
                .itemTransactionId(itemTransaction.getId())
                .orderedAt(itemTransaction.getOrderedAt())
                .totalPrice(itemTransaction.getTotalPrice())
                .build();
    }
}
