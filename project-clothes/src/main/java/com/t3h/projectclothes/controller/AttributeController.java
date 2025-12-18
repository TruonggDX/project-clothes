package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.attribute.AttributeDto;
import com.t3h.projectclothes.dto.attribute.AttributeRequest;
import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.service.AttributeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attribute")
public class AttributeController {

    private final AttributeService attributeService;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<AttributeDto>>> list() {
        List<AttributeDto> attributes = attributeService.getAll();
        return ResponseEntity.ok(BaseResponse.success(attributes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<AttributeDto>> getById(@PathVariable Long id) {
        AttributeDto attribute = attributeService.getById(id);
        return ResponseEntity.ok(BaseResponse.success(attribute));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<AttributeDto>> create(@Valid @RequestBody AttributeRequest request) {
        AttributeDto attribute = attributeService.add(request);
        return ResponseEntity.ok(BaseResponse.success(attribute));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<AttributeDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody AttributeRequest request
    ) {
        AttributeDto attribute = attributeService.update(id, request);
        return ResponseEntity.ok(BaseResponse.success(attribute));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
        attributeService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}
