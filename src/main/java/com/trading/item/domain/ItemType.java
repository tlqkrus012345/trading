package com.trading.item.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemType {

    SWORD("검"),
    SPEAR("창"),
    AXE("도끼"),
    ARMOR("방어구"),
    CONSUMABLE("소비아이템");

    private final String explain;
}
