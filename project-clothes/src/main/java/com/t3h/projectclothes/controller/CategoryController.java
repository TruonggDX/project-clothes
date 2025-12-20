package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.category.CategoryDto;
import com.t3h.projectclothes.dto.category.CategoryRequest;
import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<CategoryDto>>> list() {
        List<CategoryDto> categories = categoryService.getAll();
        return ResponseEntity.ok(BaseResponse.success(categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryDto>> getById(@PathVariable Long id) {
        CategoryDto category = categoryService.getById(id);
        return ResponseEntity.ok(BaseResponse.success(category));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CategoryDto>> create(@Valid @RequestBody CategoryRequest request) {
        CategoryDto category = categoryService.add(request);
        return ResponseEntity.ok(BaseResponse.success(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request
    ) {
        CategoryDto category = categoryService.update(id, request);
        return ResponseEntity.ok(BaseResponse.success(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}




