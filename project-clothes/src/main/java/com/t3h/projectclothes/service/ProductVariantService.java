package com.t3h.projectclothes.service;

import com.t3h.projectclothes.dto.productvariant.ProductVariantDto;
import com.t3h.projectclothes.dto.productvariant.ProductVariantRequest;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductVariantService {

    Page<ProductVariantDto> getAll(Pageable pageable);

    List<ProductVariantDto> getByProductId(Long productId);

    ProductVariantDto getById(Long id);

    ProductVariantDto add(ProductVariantRequest request);

    ProductVariantDto update(Long id, ProductVariantRequest request);

    void delete(Long id);
}

