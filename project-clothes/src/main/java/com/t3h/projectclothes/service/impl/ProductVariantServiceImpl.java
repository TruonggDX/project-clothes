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
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  public Page<ProductVariantDto> getAll(Pageable pageable) {
    return productVariantRepository.getAllProductVariant(pageable).map(productVariantMapper::toDto);
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
    ProductEntity product = getProductById(request.getProductId());
    Set<AttributeValueEntity> attributeValues = setAttributeValue(request.getAttributeValueIds());
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
    if (!entity.getProduct().getId().equals(request.getProductId())) {
      ProductEntity product = getProductById(request.getProductId());
      entity.setProduct(product);
    }
    if (Objects.nonNull(request.getAttributeValueIds()) && !request.getAttributeValueIds()
        .isEmpty()) {
      Set<AttributeValueEntity> attributeValues = setAttributeValue(request.getAttributeValueIds());
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

  private ProductEntity getProductById(Long id) {
    return productRepository.findByIdAndIsDeletedFalse(id)
        .orElseThrow(() -> BusinessException.notFound("Product not found with id: " + id));
  }

  private Set<AttributeValueEntity> setAttributeValue(Set<Long> ids) {
    Set<AttributeValueEntity> setAttributeValue = new HashSet<>();
    if (Objects.nonNull(ids) && !ids.isEmpty()) {
      List<AttributeValueEntity> entities = attributeValueRepository.findByIds(ids);
      Map<Long, AttributeValueEntity> map = entities.stream()
          .collect(Collectors.toMap(AttributeValueEntity::getId, Function.identity()));
      for (Long id : ids) {
        AttributeValueEntity attributeValue = map.get(id);
        if (Objects.isNull(attributeValue)) {
          throw BusinessException.notFound("Attribute value not found or deleted with id: " + id);
        }
        setAttributeValue.add(attributeValue);
      }
    }
    return setAttributeValue;
  }
}

