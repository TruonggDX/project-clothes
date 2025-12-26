package com.t3h.projectclothes.service;

import com.t3h.projectclothes.dto.product.ProductDto;
import com.t3h.projectclothes.dto.product.ProductFilter;
import com.t3h.projectclothes.dto.product.ProductRequest;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

  Page<ProductDto> getAllProducts(ProductFilter filter,Pageable pageable);

  ProductDto getById(Long id);

  ProductDto addProduct(ProductRequest productRequest, List<MultipartFile> files);

  ProductDto updateProduct(Long id, ProductRequest productRequest, List<MultipartFile> files);

  void deleteProduct(Long id);
}
