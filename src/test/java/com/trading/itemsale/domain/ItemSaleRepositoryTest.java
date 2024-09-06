package com.trading.itemsale.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.trading.itemsale.domain.ItemSaleInfoStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class ItemSaleRepositoryTest {

    @Autowired
    private ItemSaleRepository itemSaleRepository;

    @DisplayName("판매중, 판매완료 상태를 가진 아이템들을 조회한다")
    @Test
    void findAllByStatusIn() {
        ItemSaleInfo itemSaleInfo1 = ItemSaleInfo.builder()
                .itemName("목검")
                .status(SELLING)
                .build();

        ItemSaleInfo itemSaleInfo2 = ItemSaleInfo.builder()
                .itemName("강철검")
                .status(WAIT)
                .build();

        ItemSaleInfo itemSaleInfo3 = ItemSaleInfo.builder()
                .itemName("다이아검")
                .status(SOLD_OUT)
                .build();

        itemSaleRepository.saveAll(List.of(itemSaleInfo1, itemSaleInfo2, itemSaleInfo3));

        List<ItemSaleInfo> itemSaleInfos = itemSaleRepository.findAllByStatusIn(List.of(SELLING, SOLD_OUT));

        assertThat(itemSaleInfos).hasSize(2)
                .extracting("itemName", "status")
                .containsExactlyInAnyOrder(
                        tuple("목검", SELLING),
                        tuple("다이아검", SOLD_OUT)
                );
    }
}