package com.t3h.projectclothes.dto.productvariant;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductVariantRequest {
    
    @NotNull(message = "Product ID không được để trống")
    private Long productId;
    
    @NotNull(message = "Price không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price phải lớn hơn 0")
    private BigDecimal price;
    
    @NotNull(message = "Quantity không được để trống")
    @Min(value = 0, message = "Quantity phải lớn hơn hoặc bằng 0")
    private Long quantity;
    
    @Min(value = 0, message = "Discount phải lớn hơn hoặc bằng 0")
    private Long discount;
    
    @Size(min = 1, message = "Phải có ít nhất một attribute value")
    private Set<Long> attributeValueIds;
}

