package com.t3h.projectclothes.mapper;

import com.t3h.projectclothes.dto.productvariant.ProductVariantDto;
import com.t3h.projectclothes.dto.productvariant.ProductVariantRequest;
import com.t3h.projectclothes.entity.ProductVariantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public interface ProductVariantMapper {
    
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "attributeValueIds", expression = "java(entity.getAttributeValues() != null ? entity.getAttributeValues().stream().map(av -> av.getId()).collect(Collectors.toSet()) : null)")
    ProductVariantDto toDto(ProductVariantEntity entity);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "attributeValues", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    ProductVariantEntity toEntity(ProductVariantRequest request);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "attributeValues", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    void updateProductVariant(ProductVariantRequest request, @MappingTarget ProductVariantEntity entity);
}

