package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coupons")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends BaseEntity {

    @Column(nullable = false, unique = true, length = 255)
    String code;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    DiscountType discountType;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal discountValue;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal minOrder;

    @Column(nullable = false)
    LocalDateTime expiredAt;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<CouponUsage> couponUsages = new ArrayList<>();

    public enum DiscountType {
        PERCENTAGE, FIXED_AMOUNT
    }
}

