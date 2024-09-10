package com.trading.transaction.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTransactionRepository extends JpaRepository<ItemTransaction, Long>, ItemTransactionCustomRepository{
}
