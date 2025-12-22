package com.t3h.projectclothes.service;

import com.t3h.projectclothes.dto.productvariant.ProductVariantDto;
import com.t3h.projectclothes.dto.productvariant.ProductVariantRequest;

import java.util.List;

public interface ProductVariantService {

    List<ProductVariantDto> getAll();

    List<ProductVariantDto> getByProductId(Long productId);

    ProductVariantDto getById(Long id);

    ProductVariantDto add(ProductVariantRequest request);

    ProductVariantDto update(Long id, ProductVariantRequest request);

    void delete(Long id);
}

