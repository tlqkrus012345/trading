package com.trading.itemsale.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ItemSaleInfoTest {

    @DisplayName("재고량이 구입하는 양보다 적을 경우 정상적으로 구매 양만큼 감소한다.")
    @Test
    void reduceQuantity() {
        ItemSaleInfo itemSaleInfo = ItemSaleInfo.builder()
                .quantity(100)
                .build();

        itemSaleInfo.reduceQuantity(10);

        assertThat(itemSaleInfo.getQuantity()).isEqualTo(90);
    }

    @DisplayName("재고량보다 구입하는 양이 많으면 에러가 발생한다.")
    @Test
    void reduceQuantity2() {
        ItemSaleInfo itemSaleInfo = ItemSaleInfo.builder()
                .quantity(100)
                .build();

        assertThatThrownBy(() -> itemSaleInfo.reduceQuantity(101))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("판매 수량 부족");
    }
}