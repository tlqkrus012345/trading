package com.trading.transaction.domain;

import com.trading.transaction.api.service.PopularItemResponse;

import java.util.List;

public interface ItemTransactionCustomRepository {

    List<PopularItemResponse> findPopularItems();
}
