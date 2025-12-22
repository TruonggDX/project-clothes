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
        return ResponseEntity.ok(BaseResponse.success(categoryService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.success(categoryService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CategoryDto>> create(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(BaseResponse.success(categoryService.add(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryDto>> update(
            @PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(BaseResponse.success(categoryService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}




