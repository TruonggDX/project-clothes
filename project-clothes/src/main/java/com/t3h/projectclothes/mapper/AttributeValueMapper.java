package com.t3h.projectclothes.mapper;

import com.t3h.projectclothes.dto.attributevalue.AttributeValueDto;
import com.t3h.projectclothes.dto.attributevalue.AttributeValueRequest;
import com.t3h.projectclothes.entity.AttributeValueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttributeValueMapper {
    
    @Mapping(target = "attributeId", source = "attribute.id")
    @Mapping(target = "attributeName", source = "attribute.name")
    AttributeValueDto toDto(AttributeValueEntity entity);
    
    AttributeValueEntity toEntity(AttributeValueDto dto);
    
    void updateAttributeValue(AttributeValueRequest request, @MappingTarget AttributeValueEntity entity);
}

