package com.trading.transaction.domain;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trading.item.domain.QItem;
import com.trading.transaction.api.service.PopularItemResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ItemTransactionCustomRepositoryImpl implements ItemTransactionCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QItemTransaction itemTransaction = QItemTransaction.itemTransaction;
    private final QItem item = QItem.item;

    @Override
    public List<PopularItemResponse> findPopularItems() {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int year = today.getYear();

        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime end = today.atTime(23, 59, 59);

        return jpaQueryFactory
                .select(Projections.bean(
                        PopularItemResponse.class,
                        item.id.as("itemId"),
                        item.name.as("itemName"),
                        itemTransaction.totalQuantity.sum().as("itemSoldCnt")
                ))
                .from(itemTransaction)
                .join(item).on(itemTransaction.itemId.eq(item.id))
                .where(
                        new BooleanBuilder()
                                .and(itemTransaction.orderedAt.goe(start))
                                .and(itemTransaction.orderedAt.loe(end))
                )
                .groupBy(item.id)
                .orderBy(itemTransaction.totalQuantity.sum().desc(), item.id.asc())
                .limit(3)
                .fetch();
    }
}
