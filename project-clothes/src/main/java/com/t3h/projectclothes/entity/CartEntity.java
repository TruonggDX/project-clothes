package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "carts")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartEntity extends BaseEntity {

  @Column(nullable = false, unique = true)
  String code;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_variant_id", nullable = false)
  ProductVariantEntity productVariant;

  @Column(nullable = false)
  Integer quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  ProductEntity product;
}

