package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    Order order;

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

    public enum PaymentMethod {
        CASH, CREDIT_CARD, DEBIT_CARD, BANK_TRANSFER, E_WALLET, COD
    }

    public enum PaymentStatus {
        PENDING, PROCESSING, COMPLETED, FAILED, REFUNDED
    }
}

