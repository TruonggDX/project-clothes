package com.t3h.projectclothes.mapper;

import com.t3h.projectclothes.dto.category.CategoryDto;
import com.t3h.projectclothes.dto.category.CategoryRequest;
import com.t3h.projectclothes.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "parentId", source = "parent.id")
    CategoryDto toDto(CategoryEntity entity);

    CategoryEntity toEntity(CategoryDto dto);

    CategoryEntity toEntity(CategoryRequest request);

    void updateCategory(CategoryRequest request, @MappingTarget CategoryEntity entity);
}




