package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_variants")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @Column(length = 255)
    String size;

    @Column(length = 255)
    String color;

    @Column(nullable = false, unique = true, length = 255)
    String sku;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal price;

    @Column(nullable = false)
    Integer stock;

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<StockLog> stockLogs = new ArrayList<>();
}

