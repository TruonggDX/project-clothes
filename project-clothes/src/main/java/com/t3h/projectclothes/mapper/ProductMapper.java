package com.t3h.projectclothes.mapper;

import com.t3h.projectclothes.dto.product.ProductDto;
import com.t3h.projectclothes.dto.product.ProductRequest;
import com.t3h.projectclothes.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductDto toDto(ProductEntity product);

  ProductEntity toEntity(ProductDto productDto);

  ProductEntity toEntity(ProductRequest productRequest);
}
