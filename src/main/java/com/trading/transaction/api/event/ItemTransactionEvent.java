package com.trading.transaction.api.event;

import com.trading.transaction.api.service.ItemTransactionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ItemTransactionEvent {

    private ItemTransactionResponse itemTransactionResponse;
}
