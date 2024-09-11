package com.trading.transaction.api.service;

import com.trading.member.domain.Member;
import com.trading.member.domain.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBootTest
class ItemTransactionServiceTest {

    @Autowired
    private ItemTransactionService itemTransactionService;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Long beforePoint1 = 10000L;
        memberRepository.save(createMember(beforePoint1));

        Long beforePoint2 = 10001L;
        memberRepository.save(createMember(beforePoint2));
    }

    @DisplayName("아이템 거래 명세를 받아 거래를 생성한다.")
    @Test
    void createItemTransaction() {
        LocalDateTime now = LocalDateTime.now();
        ItemTransactionRequest request = ItemTransactionRequest.builder()
                .itemId(1L)
                .inventoryId(1L)
                .memberId(1L)
                .quantity(10)
                .itemPrice(100)
                .build();

        ItemTransactionResponse response = itemTransactionService.createItemTransaction(request, now);

        assertThat(response.getItemTransactionId()).isEqualTo(1L);
        assertThat(response.getOrderedAt()).isEqualTo(now);
        assertThat(response.getTotalPriceWithCharge()).isEqualTo(1000);
        assertThat(response.getAfterMemberPoint()).isEqualTo(9000);
    }

    @DisplayName("회원의 포인트가 부족한 경우 예외가 발생한다.")
    @Test
    void createItemTransaction2() {
        LocalDateTime now = LocalDateTime.now();
        ItemTransactionRequest request = ItemTransactionRequest.builder()
                .itemId(1L)
                .inventoryId(1L)
                .memberId(2L)
                .quantity(1)
                .itemPrice(10001)
                .build();

        assertThatThrownBy(() -> itemTransactionService.createItemTransaction(request, now))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("포인트 부족");
    }

    private Member createMember(Long point) {
        return Member.builder().point(point).build();
    }
}