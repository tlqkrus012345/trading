package com.trading.itemsale.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemSaleInfoStatus {

    SELLING("판매중"),
    SOLD_OUT("판매완료");

    private final String explain;
}
