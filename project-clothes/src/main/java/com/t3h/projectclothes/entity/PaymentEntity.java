package com.t3h.projectclothes.entity;

import com.t3h.projectclothes.enums.PaymentMethod;
import com.t3h.projectclothes.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "payments")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class PaymentEntity extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    OrderEntity order;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentMethod method;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    @Column(length = 255)
    String transactionId;

    @Column
    LocalDateTime paidAt;
}

