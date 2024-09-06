package com.trading.search.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemSearchKeywordRepository extends JpaRepository<ItemSearchKeyword, Long> {

    Optional<ItemSearchKeyword> findByItemKeyword(String keyword);

    @Query(value = "select * from item_search_keyword i order by i.item_search_count desc, i.item_keyword asc limit 3", nativeQuery = true)
    List<ItemSearchKeyword> findPopularItemKeyword();
}
