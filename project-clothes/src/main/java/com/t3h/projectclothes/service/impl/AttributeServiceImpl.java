package com.t3h.projectclothes.service.impl;

import com.t3h.projectclothes.dto.attribute.AttributeDto;
import com.t3h.projectclothes.dto.attribute.AttributeRequest;
import com.t3h.projectclothes.entity.AttributeEntity;
import com.t3h.projectclothes.exception.BusinessException;
import com.t3h.projectclothes.mapper.AttributeMapper;
import com.t3h.projectclothes.repository.AttributeRepository;
import com.t3h.projectclothes.service.AttributeService;
import com.t3h.projectclothes.utils.GenerateCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeMapper attributeMapper;

    @Override
    public List<AttributeDto> getAll() {
        List<AttributeEntity> entities = attributeRepository.getAllAttribute();
        return entities.stream().map(attributeMapper::toDto).toList();
    }

    @Override
    public AttributeDto getById(Long id) {
        AttributeEntity entity = getActiveAttribute(id);
        return attributeMapper.toDto(entity);
    }

    @Override
    public AttributeDto add(AttributeRequest attributeRequest) {
        AttributeEntity entity = attributeMapper.toEntity(attributeRequest);
        entity.setCode(GenerateCode.generateCode());
        entity.setIsDeleted(false);
        return attributeMapper.toDto(attributeRepository.save(entity));
    }

    @Override
    public AttributeDto update(Long id, AttributeRequest attributeRequest) {
        AttributeEntity entity = getActiveAttribute(id);
        attributeMapper.updateAttribute(attributeRequest,entity);
        return attributeMapper.toDto(attributeRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        AttributeEntity entity = getActiveAttribute(id);
        entity.setIsDeleted(true);
        attributeRepository.save(entity);
    }
    private AttributeEntity getActiveAttribute(Long id) {
        return attributeRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> BusinessException.notFound("Attribute not found with id: " + id));
    }
}
