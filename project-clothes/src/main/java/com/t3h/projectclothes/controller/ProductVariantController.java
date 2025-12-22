package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.dto.common.ResponsePage;
import com.t3h.projectclothes.dto.productvariant.ProductVariantDto;
import com.t3h.projectclothes.dto.productvariant.ProductVariantRequest;
import com.t3h.projectclothes.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-variant")
public class ProductVariantController {

  private final ProductVariantService productVariantService;

  @GetMapping("/list")
  public ResponseEntity<BaseResponse<ResponsePage<ProductVariantDto>>> list(Pageable pageable) {
    Page<ProductVariantDto> page = productVariantService.getAll(pageable);
    return ResponseEntity.ok(BaseResponse.success(ResponsePage.from(page)));
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<BaseResponse<List<ProductVariantDto>>> getByProductId(
      @PathVariable Long productId) {
    return ResponseEntity.ok(BaseResponse.success(productVariantService.getByProductId(productId)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<ProductVariantDto>> getById(@PathVariable Long id) {
    return ResponseEntity.ok(BaseResponse.success(productVariantService.getById(id)));
  }

  @PostMapping
  public ResponseEntity<BaseResponse<ProductVariantDto>> create(
      @Valid @RequestBody ProductVariantRequest request) {
    return ResponseEntity.ok(BaseResponse.success(productVariantService.add(request)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse<ProductVariantDto>> update(
      @PathVariable Long id, @Valid @RequestBody ProductVariantRequest request) {
    return ResponseEntity.ok(BaseResponse.success(productVariantService.update(id, request)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
    productVariantService.delete(id);
    return ResponseEntity.ok(BaseResponse.success(null));
  }
}

