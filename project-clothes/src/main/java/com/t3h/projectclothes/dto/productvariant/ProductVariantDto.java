package com.t3h.projectclothes.dto.productvariant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantDto {
    private Long id;
    private String code;
    private BigDecimal price;
    private Long quantity;
    private Long discount;
    private Long productId;
    private String productName;
    private Set<Long> attributeValueIds;
}

