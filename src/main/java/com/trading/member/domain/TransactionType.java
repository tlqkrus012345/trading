package com.trading.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {

    CHARGE("충전"),
    USE("사용");

    private final String explain;
}
