package com.t3h.projectclothes.mapper;

import com.t3h.projectclothes.dto.brand.BrandDto;
import com.t3h.projectclothes.dto.brand.BrandRequest;
import com.t3h.projectclothes.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandDto toDto(BrandEntity brandEntity);
    BrandEntity toEntity(BrandDto brandDto);
    BrandEntity toEntity(BrandRequest request);
    void updateBrand(BrandRequest request, @MappingTarget BrandEntity brandEntity);
}
