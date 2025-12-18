package com.t3h.projectclothes.mapper;

import com.t3h.projectclothes.dto.attribute.AttributeDto;
import com.t3h.projectclothes.dto.attribute.AttributeRequest;
import com.t3h.projectclothes.entity.AttributeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttributeMapper {
    AttributeDto toDto(AttributeEntity attributeEntity);
    AttributeEntity toEntity(AttributeDto attributeDto);
    AttributeEntity toEntity(AttributeRequest attributeRequest);
    void updateAttribute(AttributeRequest request, @MappingTarget AttributeEntity attributeEntity);
}
