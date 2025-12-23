package com.t3h.projectclothes.dto.product;

import com.t3h.projectclothes.utils.enums.ProductStatus;
import lombok.Data;

@Data
public class ProductRequest {

  private String name;
  private String description;
  private Long categoryId;
  private Long brandId;
  private ProductStatus status;
}
