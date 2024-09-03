package com.trading.itemsale.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ItemSaleInfoStatus {

    SELLING("판매중"),
    SOLD_OUT("판매완료"),
    WAIT("판매대기");

    private final String explain;

    public static List<ItemSaleInfoStatus> forDisplay() {
        return List.of(SELLING, SOLD_OUT);
    }
}
