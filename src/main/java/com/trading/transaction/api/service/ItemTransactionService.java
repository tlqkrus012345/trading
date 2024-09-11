package com.trading.transaction.api.service;

import com.trading.member.api.service.MemberService;
import com.trading.transaction.domain.ItemTransaction;
import com.trading.transaction.domain.ItemTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ItemTransactionService {

    private final ItemTransactionRepository itemTransactionRepository;
    private final MemberService memberService;

    @Transactional
    public ItemTransactionResponse createItemTransaction(ItemTransactionRequest request, LocalDateTime now) {
        ItemTransaction itemTransaction = ItemTransaction.create(request, now);

        Long afterMemberPoint = memberService.payPoint(itemTransaction.getMemberId(), itemTransaction.getTotalPriceWithCharge());

        return ItemTransactionResponse.of(itemTransactionRepository.save(itemTransaction), afterMemberPoint);
    }
}
