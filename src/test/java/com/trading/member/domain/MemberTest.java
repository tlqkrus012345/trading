package com.trading.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @DisplayName("회원이 포인트를 사용하면 감소한다.")
    @Test
    void payPoint() {
        Member member = Member.create("유저", 1000L, "010-1234-5678");

        member.payPoint(500);

        assertThat(member.getPoint()).isEqualTo(500L);
    }

    @DisplayName("회원이 포인트가 부족하면 예외가 발생한다.")
    @Test
    void payPoint2() {
        Member member = Member.create("유저", 1000L, "010-1234-5678");

        assertThatThrownBy(() -> member.payPoint(1001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("포인트 부족");
    }
}