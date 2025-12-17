package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "product_variants")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantEntity extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  @EqualsAndHashCode.Exclude
  ProductEntity product;

  @Column(nullable = false, unique = true)
  String code;

  @Column(nullable = false, precision = 19, scale = 2)
  BigDecimal price;

  @Column(nullable = false)
  Long quantity;

  Long discount;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinTable(
      name = "variant_attribute_value",
      joinColumns = @JoinColumn(name = "variant_id"),
      inverseJoinColumns = @JoinColumn(name = "attribute_value_id"))
  @Builder.Default
  @EqualsAndHashCode.Exclude
  Set<AttributeValueEntity> attributeValues = new HashSet<>();
}







