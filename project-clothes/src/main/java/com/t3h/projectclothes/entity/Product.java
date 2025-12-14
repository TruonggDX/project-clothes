package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Column(nullable = false, length = 255)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    Brand brand;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal basePrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ProductStatus status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<ProductImage> productImages = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<ProductVariant> productVariants = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<Review> reviews = new ArrayList<>();

    public enum ProductStatus {
        ACTIVE, INACTIVE, OUT_OF_STOCK, DISCONTINUED
    }
}

