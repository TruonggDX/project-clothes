package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_status_history")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    Order order;

    @Column(nullable = false, length = 255)
    String oldStatus;

    @Column(nullable = false, length = 255)
    String newStatus;

    @Column(nullable = false)
    LocalDateTime changedAt;
}

