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
    /**
     * GET /api/v1/attribute-value/list
     */
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<AttributeValueDto>>> list() {
        List<AttributeValueDto> attributeValues = attributeValueService.getAll();
        return ResponseEntity.ok(BaseResponse.success(attributeValues));
    }
    /**
     * GET /api/v1/attribute-value/attribute/{attributeId}
     */
    @GetMapping("/attribute/{attributeId}")
    public ResponseEntity<BaseResponse<List<AttributeValueDto>>> getByAttributeId(
            @PathVariable Long attributeId
    ) {
        List<AttributeValueDto> attributeValues = attributeValueService.getByAttributeId(attributeId);
        return ResponseEntity.ok(BaseResponse.success(attributeValues));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<AttributeValueDto>> getById(@PathVariable Long id) {
        AttributeValueDto attributeValue = attributeValueService.getById(id);
        return ResponseEntity.ok(BaseResponse.success(attributeValue));
    }
    /**
     * POST /api/v1/attribute-value
     * Body: { "value": "Red", "attributeId": 1 }
     */
    @PostMapping
    public ResponseEntity<BaseResponse<AttributeValueDto>> create(
            @Valid @RequestBody AttributeValueRequest request
    ) {
        AttributeValueDto attributeValue = attributeValueService.add(request);
        return ResponseEntity.ok(BaseResponse.success(attributeValue));
    }
    /**
     * PUT /api/v1/attribute-value/{id}
     * Body: { "value": "Blue", "attributeId": 1 }
     */
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<AttributeValueDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody AttributeValueRequest request
    ) {
        AttributeValueDto attributeValue = attributeValueService.update(id, request);
        return ResponseEntity.ok(BaseResponse.success(attributeValue));
    }
    /**
     * DELETE /api/v1/attribute-value/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
        attributeValueService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}

