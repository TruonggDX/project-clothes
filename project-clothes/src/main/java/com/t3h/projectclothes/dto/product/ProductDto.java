package com.t3h.projectclothes.dto.product;

import com.t3h.projectclothes.dto.brand.BrandDto;
import com.t3h.projectclothes.dto.category.CategoryResponse;
import com.t3h.projectclothes.dto.image.ImageDto;
import com.t3h.projectclothes.utils.enums.ProductStatus;
import java.util.List;
import lombok.Data;

@Data
public class ProductDto {

  private Long id;
  private String code;
  private String name;
  private String description;
  private CategoryResponse category;
  private BrandDto brand;
  private ProductStatus status;
  private List<ImageDto> images;
}
