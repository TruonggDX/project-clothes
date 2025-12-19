package com.t3h.projectclothes.service;

import com.t3h.projectclothes.dto.attribute.AttributeDto;
import com.t3h.projectclothes.dto.attribute.AttributeRequest;
import com.t3h.projectclothes.dto.brand.BrandDto;

import java.util.List;

public interface AttributeService {

    List<AttributeDto> getAll();

    AttributeDto getById(Long id);

    AttributeDto add(AttributeRequest attributeRequest);

    AttributeDto update(Long id, AttributeRequest attributeRequest);

    void delete(Long id);
}
