package com.trading.transaction.api.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ItemTransactionServiceTest {

    @Autowired
    private ItemTransactionService itemTransactionService;

    @DisplayName("아이템 거래 명세를 받아 거래를 생성한다.")
    @Test
    void createItemTransaction() {
        LocalDateTime now = LocalDateTime.now();
        ItemTransactionRequest request = ItemTransactionRequest.builder()
                .itemId(1L)
                .inventoryId(1L)
                .memberId(1L)
                .quantity(10)
                .build();

        ItemTransactionResponse response = itemTransactionService.createItemTransaction(request, now);

        assertThat(response.getItemTransactionId()).isEqualTo(1L);
        assertThat(response.getOrderedAt()).isEqualTo(now);
    }
}