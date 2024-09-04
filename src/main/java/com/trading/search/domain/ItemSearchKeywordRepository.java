package com.trading.search.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemSearchKeywordRepository extends JpaRepository<ItemSearchKeyword, Long> {

    Optional<ItemSearchKeyword> findByItemKeyword(String keyword);
}
