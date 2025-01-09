package com.trading.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KafkaTopic {

    ITEM_TRANSACTION_CREATED("item-transaction");

    private final String topic;
}