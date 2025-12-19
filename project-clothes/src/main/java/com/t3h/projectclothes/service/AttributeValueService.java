package com.t3h.projectclothes.service;

import com.t3h.projectclothes.dto.attributevalue.AttributeValueDto;
import com.t3h.projectclothes.dto.attributevalue.AttributeValueRequest;

import java.util.List;

public interface AttributeValueService {
    
    List<AttributeValueDto> getAll();
    
    List<AttributeValueDto> getByAttributeId(Long attributeId);
    
    AttributeValueDto getById(Long id);
    
    AttributeValueDto add(AttributeValueRequest request);
    
    AttributeValueDto update(Long id, AttributeValueRequest request);
    
    void delete(Long id);
}

