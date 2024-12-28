package com.trading.member.api.service;

import com.trading.member.domain.Member;
import com.trading.member.domain.MemberRepository;
import com.trading.transaction.domain.ItemTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long payPoint(Long memberId, int payPoint) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 member id: " + memberId));

        return member.payPoint(payPoint);
    }
}
