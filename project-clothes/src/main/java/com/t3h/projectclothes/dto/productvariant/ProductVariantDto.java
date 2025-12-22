package com.t3h.projectclothes.dto.productvariant;

import com.t3h.projectclothes.dto.attributevalue.AttributeValueResponse;
import com.t3h.projectclothes.dto.product.ProductResponse;
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
    private ProductResponse product;
    private Set<AttributeValueResponse> attributeValues;
}

