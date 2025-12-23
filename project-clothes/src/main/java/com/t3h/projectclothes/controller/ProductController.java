package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.dto.common.ResponsePage;
import com.t3h.projectclothes.dto.product.ProductDto;
import com.t3h.projectclothes.dto.product.ProductFilter;
import com.t3h.projectclothes.dto.product.ProductRequest;
import com.t3h.projectclothes.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/list")
  public ResponseEntity<BaseResponse<ResponsePage<ProductDto>>> list(@ParameterObject ProductFilter productFilter,
      Pageable pageable) {
    Page<ProductDto> page = productService.getAllProducts(productFilter, pageable);
    return ResponseEntity.ok(BaseResponse.success(ResponsePage.from(page)));
  }

  @PostMapping()
  public ResponseEntity<BaseResponse<ProductDto>> addProduct(
      @Valid @ModelAttribute ProductRequest request,
      @RequestBody(required = false) List<MultipartFile> files) {
    return ResponseEntity.ok(BaseResponse.success(productService.addProduct(request, files)));
  }
}
