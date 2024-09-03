package com.trading.itemsale.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemSaleRepository extends JpaRepository<ItemSaleInfo, Long> {

    List<ItemSaleInfo> findAllByStatusIn(List<ItemSaleInfoStatus> itemSaleInfoStatuses);
}
