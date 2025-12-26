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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<ProductDto>> getById(@PathVariable Long id) {
    return ResponseEntity.ok(BaseResponse.success(productService.getById(id)));
  }

  @PostMapping()
  public ResponseEntity<BaseResponse<ProductDto>> addProduct(
      @Valid @ModelAttribute ProductRequest request,
      @RequestParam(value = "files", required = false) List<MultipartFile> files) {
    return ResponseEntity.ok(BaseResponse.success(productService.addProduct(request, files)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse<ProductDto>> updateProduct(
      @PathVariable Long id,
      @Valid @ModelAttribute ProductRequest request,
      @RequestParam(value = "files", required = false) List<MultipartFile> files) {
    return ResponseEntity.ok(BaseResponse.success(productService.updateProduct(id, request, files)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse<Void>> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.ok(BaseResponse.success(null));
  }
}
