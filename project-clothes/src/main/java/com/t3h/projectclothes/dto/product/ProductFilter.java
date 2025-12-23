package com.t3h.projectclothes.dto.product;

import com.t3h.projectclothes.utils.enums.ProductStatus;
import lombok.Data;

@Data
public class ProductFilter {

  private String code;
  private String name;
  private Long categoryId;
  private Long brandId;
  private ProductStatus status;
}
