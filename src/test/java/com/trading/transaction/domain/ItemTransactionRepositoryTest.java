package com.trading.transaction.domain;

import com.trading.item.domain.Item;
import com.trading.item.domain.ItemRepository;
import com.trading.item.domain.ItemType;
import com.trading.transaction.api.service.PopularItemResponse;
import com.trading.transaction.api.service.PopularItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ItemTransactionRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemTransactionRepository itemTransactionRepository;

    @AfterEach
    void tearDown() {
        itemTransactionRepository.deleteAllInBatch();
    }

    @DisplayName("가장 많이 판매된 아이템 3개를 조회한다.")
    @Test
    void findPopularItems() {
        LocalDateTime now = LocalDateTime.now();

        String itemName1 = "아이템 1";
        String itemName2 = "아이템 2";
        String itemName3 = "아이템 3";
        String itemName4 = "아이템 4";
        Item item1 = createItem(itemName1);
        Item item2 = createItem(itemName2);
        Item item3 = createItem(itemName3);
        Item item4 = createItem(itemName4);
        itemRepository.saveAll(List.of(item1, item2, item3, item4));

        Long itemId1 = 1L;
        int itemQuantity1 = 100;
        Long itemId2 = 2L;
        int itemQuantity2 = 80;
        Long itemId3 = 3L;
        int itemQuantity3 = 30;
        Long itemId4 = 4L;
        int itemQuantity4 = 10;
        ItemTransaction itemTransaction1 = createItemTransaction(itemId1, now, itemQuantity1);

        ItemTransaction itemTransaction2 = createItemTransaction(itemId2, now, itemQuantity2);
        ItemTransaction itemTransaction3 = createItemTransaction(itemId2, now, itemQuantity2);

        ItemTransaction itemTransaction4 = createItemTransaction(itemId3, now, itemQuantity3);

        ItemTransaction itemTransaction5 = createItemTransaction(itemId4, now, itemQuantity4);
        itemTransactionRepository.saveAll(List.of(itemTransaction1, itemTransaction2, itemTransaction3, itemTransaction4, itemTransaction5));

        List<PopularItemResponse> popularItems = itemTransactionRepository.findPopularItems();

        assertThat(popularItems).hasSize(3)
                .extracting("itemName", "itemId", "itemSoldCnt")
                .containsExactly(
                        tuple("아이템 2", 2L, 160),
                        tuple("아이템 1", 1L, 100),
                        tuple("아이템 3", 3L, 30)
                );
    }

    @DisplayName("가장 많이 판매된 아이템이 중복이 된 경우 아이템 id가 낮은 순으로 조회한다.")
    @Test
    void findPopularItems2() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        String itemName1 = "아이템 1";
        String itemName2 = "아이템 2";
        String itemName3 = "아이템 3";
        String itemName4 = "아이템 4";
        Item item1 = createItem(itemName1);
        Item item2 = createItem(itemName2);
        Item item3 = createItem(itemName3);
        Item item4 = createItem(itemName4);
        itemRepository.saveAll(List.of(item1, item2, item3, item4));

        Long itemId1 = 1L;
        int itemQuantity1 = 100;
        Long itemId2 = 2L;
        int itemQuantity2 = 100;
        Long itemId3 = 3L;
        int itemQuantity3 = 100;
        Long itemId4 = 4L;
        int itemQuantity4 = 100;
        ItemTransaction itemTransaction1 = createItemTransaction(itemId1, now, itemQuantity1);
        ItemTransaction itemTransaction2 = createItemTransaction(itemId2, now, itemQuantity2);
        ItemTransaction itemTransaction4 = createItemTransaction(itemId3, now, itemQuantity3);
        ItemTransaction itemTransaction5 = createItemTransaction(itemId4, now, itemQuantity4);
        itemTransactionRepository.saveAll(List.of(itemTransaction1, itemTransaction2, itemTransaction4, itemTransaction5));

        List<PopularItemResponse> popularItems = itemTransactionRepository.findPopularItems();

        assertThat(popularItems).hasSize(3)
                .extracting("itemName", "itemId", "itemSoldCnt")
                .containsExactly(
                        tuple("아이템 1", 1L, 100),
                        tuple("아이템 2", 2L, 100),
                        tuple("아이템 3", 3L, 100)
                );
    }

    private ItemTransaction createItemTransaction(Long itemId1, LocalDateTime now, int quantity) {
        return ItemTransaction.builder()
                .itemId(itemId1)
                .memberId(1L)
                .status(ItemTransactionStatus.COMPLETED)
                .totalPrice(1000)
                .totalQuantity(quantity)
                .inventoryId(1L)
                .orderedAt(now)
                .build();
    }

    private Item createItem(String itemName1) {
        return Item.builder()
                .name(itemName1)
                .type(ItemType.SPEAR)
                .price(100)
                .description("아이템1")
                .build();
    }
}