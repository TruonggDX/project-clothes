package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "attribute_value")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AttributeValueEntity extends BaseEntity {

    @Column(nullable = false, length = 255)
    String value;

    @Column(length = 255)
    String colorCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    @EqualsAndHashCode.Exclude
    AttributeEntity attribute;

    @ManyToMany(mappedBy = "attributeValues", fetch = FetchType.LAZY)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    Set<ProductVariantEntity> productVariants = new HashSet<>();
}

