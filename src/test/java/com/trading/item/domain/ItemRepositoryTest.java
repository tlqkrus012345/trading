package com.trading.item.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.trading.item.domain.ItemType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @DisplayName("아이템 이름을 통해 조회한다.")
    @Test
    void findByNameContaining() {
        Item item1 = Item.builder()
                .name("강철검")
                .price(1200)
                .type(SWORD)
                .description("일반 강철검")
                .build();
        Item item2 = Item.builder()
                .name("강철의 창")
                .price(3000)
                .type(SPEAR)
                .description("특수 강철 창")
                .build();
        Item item3 = Item.builder()
                .name("철의 도끼")
                .price(3000)
                .type(AXE)
                .description("특수 강철 도끼")
                .build();
        itemRepository.saveAll(List.of(item1, item2, item3));

        String itemKeyword = "강철";

        List<Item> items = itemRepository.findByNameContaining(itemKeyword);

        assertThat(items).hasSize(2)
                .extracting("name", "price", "type", "description")
                .containsExactlyInAnyOrder(
                        tuple("강철검", 1200, SWORD, "일반 강철검"),
                        tuple("강철의 창", 3000, SPEAR, "특수 강철 창")
                );
    }
}