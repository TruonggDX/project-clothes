package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.brand.BrandDto;
import com.t3h.projectclothes.dto.brand.BrandRequest;
import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class BrandController {

    private final BrandService brandService;
    @GetMapping("/list")
    public ResponseEntity<List<BrandDto>> list() {
        List<BrandDto> brands = brandService.getAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<BrandDto>> getById(@PathVariable Long id) {
        BrandDto brand = brandService.getById(id);
        return ResponseEntity.ok(BaseResponse.success(brand));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<BrandDto>> create(@Valid @RequestBody BrandRequest request) {
        BrandDto brand = brandService.add(request);
        return ResponseEntity.ok(BaseResponse.success(brand));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<BrandDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody BrandRequest request
    ) {
        BrandDto brand = brandService.update(id, request);
        return ResponseEntity.ok(BaseResponse.success(brand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}



