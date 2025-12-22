package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.dto.productvariant.ProductVariantDto;
import com.t3h.projectclothes.dto.productvariant.ProductVariantRequest;
import com.t3h.projectclothes.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-variant")
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    /**
     * GET /api/v1/product-variant/list
     */
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<ProductVariantDto>>> list() {
        List<ProductVariantDto> productVariants = productVariantService.getAll();
        return ResponseEntity.ok(BaseResponse.success(productVariants));
    }

    /**
     * GET /api/v1/product-variant/product/{productId}
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<BaseResponse<List<ProductVariantDto>>> getByProductId(
            @PathVariable Long productId
    ) {
        List<ProductVariantDto> productVariants = productVariantService.getByProductId(productId);
        return ResponseEntity.ok(BaseResponse.success(productVariants));
    }

    /**
     * GET /api/v1/product-variant/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductVariantDto>> getById(@PathVariable Long id) {
        ProductVariantDto productVariant = productVariantService.getById(id);
        return ResponseEntity.ok(BaseResponse.success(productVariant));
    }

    /**
     * POST /api/v1/product-variant
     * Body: { "productId": 1, "price": 100000, "quantity": 10, "discount": 0, "attributeValueIds": [1, 2] }
     */
    @PostMapping
    public ResponseEntity<BaseResponse<ProductVariantDto>> create(
            @Valid @RequestBody ProductVariantRequest request
    ) {
        ProductVariantDto productVariant = productVariantService.add(request);
        return ResponseEntity.ok(BaseResponse.success(productVariant));
    }

    /**
     * PUT /api/v1/product-variant/{id}
     * Body: { "productId": 1, "price": 120000, "quantity": 15, "discount": 10, "attributeValueIds": [1, 3] }
     */
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductVariantDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductVariantRequest request
    ) {
        ProductVariantDto productVariant = productVariantService.update(id, request);
        return ResponseEntity.ok(BaseResponse.success(productVariant));
    }

    /**
     * DELETE /api/v1/product-variant/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
        productVariantService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}

