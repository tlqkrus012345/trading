package com.trading.transaction.api.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularItemResponse {

    private Long itemId;
    private String itemName;
    private int itemSoldCnt;
}
