package com.trading.transaction.api.service;


import com.trading.item.domain.ItemType;
import com.trading.itemsale.domain.ItemSaleInfo;
import com.trading.itemsale.domain.ItemSaleInfoStatus;
import com.trading.itemsale.domain.ItemSaleRepository;
import com.trading.member.domain.Member;
import com.trading.member.domain.MemberRepository;
import com.trading.transaction.domain.ItemTransactionRepository;
import org.junit.jupiter.api.AfterEach;
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
    private ItemTransactionRepository itemTransactionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        itemTransactionRepository.deleteAll();
    }

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
                .quantity(1000)
                .build();
        itemSaleRepository.save(beforeItemSaleInfo);
    }

    @Test
    void createItemTransaction() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        ItemTransactionRequest request = ItemTransactionRequest.builder()
                .itemId(1L)
                .inventoryId(1L)
                .memberId(1L)
                .itemSaleId(1L)
                .quantity(1)
                .itemPrice(100)
                .build();

        int count = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        CountDownLatch latch = new CountDownLatch(count);

        for (int i=0; i<count; i++) {
            executorService.submit(() -> {
                try {
                    itemTransactionService.createItemTransaction(request, now);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        Member member = memberRepository.findById(request.getMemberId()).get();
        Long memberPoint = member.getPoint();
        assertThat(memberPoint).isEqualTo(10000L - 1000);
    }

    private Member createMember(Long point) {
        return Member.builder().point(point).build();
    }
}
