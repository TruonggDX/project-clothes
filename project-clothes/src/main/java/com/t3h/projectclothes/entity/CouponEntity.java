package com.t3h.projectclothes.entity;

import com.t3h.projectclothes.enums.DiscountType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "coupons")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CouponEntity extends BaseEntity {

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
}

