package com.trading.transaction.domain;

import com.trading.common.BaseEntity;
import com.trading.transaction.api.service.ItemTransactionRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ItemTransaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long inventoryId;

    private Long itemId;

    private Long itemSaleId;

    private int totalPrice;

    private int totalPriceWithCharge;

    private int totalQuantity;

    @Enumerated(value = EnumType.STRING)
    private ItemTransactionStatus status;

    private LocalDateTime orderedAt;

    private ItemTransaction(ItemTransactionRequest request, LocalDateTime now) {
        this.memberId = request.getMemberId();
        this.inventoryId = request.getInventoryId();
        this.itemId = request.getItemId();
        this.itemSaleId = request.getItemSaleId();
        this.status = ItemTransactionStatus.COMPLETED;
        this.orderedAt = now;
        this.totalQuantity = request.getQuantity();
        this.totalPrice = calTotalPrice(request.getQuantity(), request.getItemPrice());
        this.totalPriceWithCharge = calTotalPriceWithCharge(totalPrice);
    }

    public static ItemTransaction create(ItemTransactionRequest request, LocalDateTime now) {
        return new ItemTransaction(request, now);
    }

    private int calTotalPrice(int quantity, int price) {
        return quantity * price;
    }

    private int calTotalPriceWithCharge(int totalPrice) {
        if (totalPrice <= 10000) {
            return totalPrice;
        }

        if (totalPrice <= 100000) {
            return (int) Math.round(totalPrice + (totalPrice * 0.05));
        }

        return (int) Math.round(totalPrice + (totalPrice * 0.1));
    }
}
