package com.t3h.projectclothes.entity;

import com.t3h.projectclothes.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class OrderEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    AddressEntity address;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal totalPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    OrderStatus status;
}

