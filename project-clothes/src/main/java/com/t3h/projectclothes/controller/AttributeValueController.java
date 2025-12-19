package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.attributevalue.AttributeValueDto;
import com.t3h.projectclothes.dto.attributevalue.AttributeValueRequest;
import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.service.AttributeValueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attribute-value")
public class AttributeValueController {

    private final AttributeValueService attributeValueService;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<AttributeValueDto>>> list() {
        return ResponseEntity.ok(BaseResponse.success(attributeValueService.getAll()));
    }

    @GetMapping("/attribute/{attributeId}")
    public ResponseEntity<BaseResponse<List<AttributeValueDto>>> getByAttributeId(
            @PathVariable Long attributeId) {
        return ResponseEntity.ok(BaseResponse.success(attributeValueService.getByAttributeId(attributeId)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<AttributeValueDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.success(attributeValueService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<AttributeValueDto>> create(
            @Valid @RequestBody AttributeValueRequest request) {
        return ResponseEntity.ok(BaseResponse.success(attributeValueService.add(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<AttributeValueDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody AttributeValueRequest request) {
        return ResponseEntity.ok(BaseResponse.success(attributeValueService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
        attributeValueService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}

