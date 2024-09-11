package com.trading.member.domain;

import com.trading.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long point;

    private String phone;

    @Builder
    private Member(String name, Long point, String phone) {
        this.name = name;
        this.point = point;
        this.phone = phone;
    }

    public static Member create(String name, Long point, String phone) {
        return Member.builder()
                .name(name)
                .point(point)
                .phone(phone)
                .build();
    }

    public Long payPoint(int payPoint) {
        if (point < payPoint) {
            throw new IllegalArgumentException("포인트 부족");
        }
        point -= payPoint;
        return point;
    }
}
