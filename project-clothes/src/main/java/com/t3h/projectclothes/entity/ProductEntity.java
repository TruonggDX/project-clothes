package com.t3h.projectclothes.entity;

import com.t3h.projectclothes.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ProductEntity extends BaseEntity {

    @Column(nullable = false, length = 255)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    BrandEntity brand;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal basePrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ProductStatus status;
}

