package com.trading.transaction.api.service;

import com.trading.transaction.domain.ItemTransaction;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemTransactionResponse {

    private Long itemTransactionId;
    private LocalDateTime orderedAt;
    private int totalPriceWithCharge;
    private Long afterMemberPoint;

    @Builder
    private ItemTransactionResponse(Long itemTransactionId, LocalDateTime orderedAt, int totalPriceWithCharge, Long afterMemberPoint) {
        this.itemTransactionId = itemTransactionId;
        this.orderedAt = orderedAt;
        this.totalPriceWithCharge = totalPriceWithCharge;
        this.afterMemberPoint = afterMemberPoint;
    }

    public static ItemTransactionResponse of(ItemTransaction itemTransaction, Long afterMemberPoint) {
        return ItemTransactionResponse.builder()
                .itemTransactionId(itemTransaction.getId())
                .orderedAt(itemTransaction.getOrderedAt())
                .totalPriceWithCharge(itemTransaction.getTotalPriceWithCharge())
                .afterMemberPoint(afterMemberPoint)
                .build();
    }
}
