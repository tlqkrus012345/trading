package com.trading.transaction.api.service;


import com.trading.item.domain.ItemType;
import com.trading.itemsale.domain.ItemSaleInfo;
import com.trading.itemsale.domain.ItemSaleInfoStatus;
import com.trading.itemsale.domain.ItemSaleRepository;
import com.trading.member.domain.Member;
import com.trading.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class ItemTransactionConcurrencyTest {

    @Autowired
    private ItemTransactionService itemTransactionService;

    @Autowired
    private ItemSaleRepository itemSaleRepository;

    @Autowired
    private MemberRepository memberRepository;

    int beforeQuantity = 1000;

    @BeforeEach
    void setUp() {
        Long beforePoint1 = 10000L;
        memberRepository.save(createMember(beforePoint1));

        Long beforePoint2 = 10001L;
        memberRepository.save(createMember(beforePoint2));

        ItemSaleInfo beforeItemSaleInfo = ItemSaleInfo.builder()
                .itemName("아이템")
                .itemType(ItemType.SWORD)
                .itemId(1L)
                .status(ItemSaleInfoStatus.SELLING)
                .itemPrice(100)
                .memberName("유저")
                .description("아 이 템")
                .inventoryId(1L)
                .quantity(beforeQuantity)
                .build();
        itemSaleRepository.save(beforeItemSaleInfo);
    }

    @Test
    void createItemTransaction() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        int buyQuantity = 10;
        ItemTransactionRequest request = ItemTransactionRequest.builder()
                .itemId(1L)
                .inventoryId(1L)
                .memberId(1L)
                .itemSaleId(1L)
                .quantity(buyQuantity)
                .itemPrice(100)
                .build();

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> itemTransactionService.createItemTransaction(request, now)),
                CompletableFuture.runAsync(() -> itemTransactionService.createItemTransaction(request, now))
        ).join();

        ItemSaleInfo afterItemSaleInfo = itemSaleRepository.findById(1L).orElseThrow();
        int afterQuantity = afterItemSaleInfo.getQuantity();

        assertThat(afterQuantity).isEqualTo(beforeQuantity - (buyQuantity * 2));
    }

    private Member createMember(Long point) {
        return Member.builder().point(point).build();
    }
}
