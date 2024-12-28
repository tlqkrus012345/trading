package com.trading.transaction.domain;

import com.trading.transaction.api.service.ItemTransactionRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemTransactionTest {

    @DisplayName("만원 이하의 금액은 수수료가 붙지 않고 (아이템 가격 * 수량) 이다.")
    @Test
    void getTotalPriceWithCharge() {
        LocalDateTime now = LocalDateTime.now();
        ItemTransactionRequest request = ItemTransactionRequest.builder()
                .itemId(1L)
                .inventoryId(1L)
                .memberId(1L)
                .itemPrice(1000)
                .quantity(10)
                .build();
        ItemTransaction itemTransaction = ItemTransaction.create(request, now);

        assertThat(itemTransaction.getTotalPriceWithCharge()).isEqualTo(10000);
    }

    @DisplayName("만원 초과의 금액은 총 금액 5%의 수수료가 붙는다.")
    @Test
    void getTotalPriceWithCharge2() {
        LocalDateTime now = LocalDateTime.now();
        ItemTransactionRequest request = ItemTransactionRequest.builder()
                .itemId(1L)
                .inventoryId(1L)
                .memberId(1L)
                .itemPrice(10001)
                .quantity(1)
                .build();
        ItemTransaction itemTransaction = ItemTransaction.create(request, now);

        assertThat(itemTransaction.getTotalPriceWithCharge()).isEqualTo(10501);
    }

    @DisplayName("십만원 이하의 금액은 총 금액 5%의 수수료가 붙는다.")
    @Test
    void getTotalPriceWithCharge3() {
        LocalDateTime now = LocalDateTime.now();
        ItemTransactionRequest request = ItemTransactionRequest.builder()
                .itemId(1L)
                .inventoryId(1L)
                .memberId(1L)
                .itemPrice(100000)
                .quantity(1)
                .build();
        ItemTransaction itemTransaction = ItemTransaction.create(request, now);

        assertThat(itemTransaction.getTotalPriceWithCharge()).isEqualTo(105000);
    }

    @DisplayName("십만원 초과의 금액은 총 금액 10%의 수수료가 붙는다.")
    @Test
    void getTotalPriceWithCharge4() {
        LocalDateTime now = LocalDateTime.now();
        ItemTransactionRequest request = ItemTransactionRequest.builder()
                .itemId(1L)
                .inventoryId(1L)
                .memberId(1L)
                .itemPrice(100001)
                .quantity(1)
                .build();
        ItemTransaction itemTransaction = ItemTransaction.create(request, now);

        assertThat(itemTransaction.getTotalPriceWithCharge()).isEqualTo(110001);
    }

    @DisplayName("소수점 첫 번째 자리에서 반올림이 된다")
    @Test
    void getTotalPriceWithCharge5() {
        LocalDateTime now = LocalDateTime.now();
        ItemTransactionRequest request = ItemTransactionRequest.builder()
                .itemId(1L)
                .inventoryId(1L)
                .memberId(1L)
                .itemPrice(10099)
                .quantity(1)
                .build();
        ItemTransaction itemTransaction = ItemTransaction.create(request, now);

        assertThat(itemTransaction.getTotalPriceWithCharge()).isEqualTo(10604);
    }
}