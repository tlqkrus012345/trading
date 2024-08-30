package com.trading.transaction.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemTransactionStatus {

    COMPLETED("처리완료"),
    CANCELED("취소");

    private final String explain;
}
