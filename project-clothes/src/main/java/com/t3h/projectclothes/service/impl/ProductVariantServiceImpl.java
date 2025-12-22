package com.t3h.projectclothes.service.impl;

import com.t3h.projectclothes.dto.productvariant.ProductVariantDto;
import com.t3h.projectclothes.dto.productvariant.ProductVariantRequest;
import com.t3h.projectclothes.entity.AttributeValueEntity;
import com.t3h.projectclothes.entity.ProductEntity;
import com.t3h.projectclothes.entity.ProductVariantEntity;
import com.t3h.projectclothes.exception.BusinessException;
import com.t3h.projectclothes.mapper.ProductVariantMapper;
import com.t3h.projectclothes.repository.AttributeValueRepository;
import com.t3h.projectclothes.repository.ProductRepository;
import com.t3h.projectclothes.repository.ProductVariantRepository;
import com.t3h.projectclothes.service.ProductVariantService;
import com.t3h.projectclothes.utils.GenerateCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    private final AttributeValueRepository attributeValueRepository;
    private final ProductVariantMapper productVariantMapper;

    @Override
    public List<ProductVariantDto> getAll() {
        List<ProductVariantEntity> entities = productVariantRepository.getAllProductVariant();
        return entities.stream().map(productVariantMapper::toDto).toList();
    }

    @Override
    public List<ProductVariantDto> getByProductId(Long productId) {
        List<ProductVariantEntity> entities = productVariantRepository.findByProductId(productId);
        return entities.stream().map(productVariantMapper::toDto).toList();
    }

    @Override
    public ProductVariantDto getById(Long id) {
        ProductVariantEntity entity = getActiveProductVariant(id);
        return productVariantMapper.toDto(entity);
    }

    @Override
    public ProductVariantDto add(ProductVariantRequest request) {
        // Validate product exists
        ProductEntity product = productRepository.findByIdAndIsDeletedFalse(request.getProductId())
                .orElseThrow(() -> BusinessException.notFound("Product not found with id: " + request.getProductId()));

        // Validate attribute values exist
        Set<AttributeValueEntity> attributeValues = new HashSet<>();
        if (request.getAttributeValueIds() != null && !request.getAttributeValueIds().isEmpty()) {
            for (Long attributeValueId : request.getAttributeValueIds()) {
                AttributeValueEntity attributeValue = attributeValueRepository.findByIdAndIsDeletedFalse(attributeValueId)
                        .orElseThrow(() -> BusinessException.notFound("Attribute value not found with id: " + attributeValueId));
                attributeValues.add(attributeValue);
            }
        }

        ProductVariantEntity entity = productVariantMapper.toEntity(request);
        entity.setProduct(product);
        entity.setAttributeValues(attributeValues);
        entity.setCode(GenerateCode.generateCode());
        entity.setIsDeleted(false);

        return productVariantMapper.toDto(productVariantRepository.save(entity));
    }

    @Override
    public ProductVariantDto update(Long id, ProductVariantRequest request) {
        ProductVariantEntity entity = getActiveProductVariant(id);

        // Validate product exists if changed
        if (!entity.getProduct().getId().equals(request.getProductId())) {
            ProductEntity product = productRepository.findByIdAndIsDeletedFalse(request.getProductId())
                    .orElseThrow(() -> BusinessException.notFound("Product not found with id: " + request.getProductId()));
            entity.setProduct(product);
        }

        // Update attribute values
        if (request.getAttributeValueIds() != null && !request.getAttributeValueIds().isEmpty()) {
            Set<AttributeValueEntity> attributeValues = new HashSet<>();
            for (Long attributeValueId : request.getAttributeValueIds()) {
                AttributeValueEntity attributeValue = attributeValueRepository.findByIdAndIsDeletedFalse(attributeValueId)
                        .orElseThrow(() -> BusinessException.notFound("Attribute value not found with id: " + attributeValueId));
                attributeValues.add(attributeValue);
            }
            entity.setAttributeValues(attributeValues);
        }

        productVariantMapper.updateProductVariant(request, entity);
        return productVariantMapper.toDto(productVariantRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        ProductVariantEntity entity = getActiveProductVariant(id);
        entity.setIsDeleted(true);
        productVariantRepository.save(entity);
    }

    private ProductVariantEntity getActiveProductVariant(Long id) {
        return productVariantRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> BusinessException.notFound("Product variant not found with id: " + id));
    }
}

