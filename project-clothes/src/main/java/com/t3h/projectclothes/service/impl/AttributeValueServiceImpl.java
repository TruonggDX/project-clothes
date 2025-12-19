package com.t3h.projectclothes.service.impl;

import com.t3h.projectclothes.dto.attributevalue.AttributeValueDto;
import com.t3h.projectclothes.dto.attributevalue.AttributeValueRequest;
import com.t3h.projectclothes.entity.AttributeEntity;
import com.t3h.projectclothes.entity.AttributeValueEntity;
import com.t3h.projectclothes.exception.BusinessException;
import com.t3h.projectclothes.mapper.AttributeValueMapper;
import com.t3h.projectclothes.repository.AttributeRepository;
import com.t3h.projectclothes.repository.AttributeValueRepository;
import com.t3h.projectclothes.service.AttributeValueService;
import com.t3h.projectclothes.utils.GenerateCode;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AttributeValueServiceImpl implements AttributeValueService {

    private final AttributeValueRepository attributeValueRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeValueMapper attributeValueMapper;

    @Override
    public List<AttributeValueDto> getAll() {
        List<AttributeValueEntity> entities = attributeValueRepository.getAllAttributeValue();
        return entities.stream().map(attributeValueMapper::toDto).toList();
    }

    @Override
    public List<AttributeValueDto> getByAttributeId(Long attributeId) {
        List<AttributeValueEntity> entities = attributeValueRepository.findByAttributeId(attributeId);
        return entities.stream().map(attributeValueMapper::toDto).toList();
    }

    @Override
    public AttributeValueDto getById(Long id) {
        AttributeValueEntity entity = getActiveAttributeValue(id);
        return attributeValueMapper.toDto(entity);
    }

    @Override
    public AttributeValueDto add(AttributeValueRequest request) {
        AttributeEntity attribute = getAttributeById(request.getAttributeId());
        inValidDataAdd(request);
        AttributeValueEntity entity = attributeValueMapper.toRequest(request);
        entity.setCode(GenerateCode.generateCode());
        entity.setAttribute(attribute);
        entity.setIsDeleted(false);
        return attributeValueMapper.toDto(attributeValueRepository.save(entity));
    }

    @Override
    public AttributeValueDto update(Long id, AttributeValueRequest request) {
        AttributeValueEntity entity = getActiveAttributeValue(id);
        AttributeEntity attribute = getAttributeById(request.getAttributeId());
        inValidDataUpdate(request,entity);
        entity.setValue(request.getValue());
        entity.setAttribute(attribute);
        return attributeValueMapper.toDto(attributeValueRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        AttributeValueEntity entity = getActiveAttributeValue(id);
        entity.setIsDeleted(true);
        attributeValueRepository.save(entity);
    }

    private void inValidDataUpdate(AttributeValueRequest request, AttributeValueEntity entity){
        if (attributeValueRepository.existsByValueAndAttributeId(request.getValue(), request.getAttributeId())
            && !entity.getValue().equals(request.getValue())) {
            throw BusinessException.badRequest("Value đã tồn tại trong attribute này");
        }
    }

    private void inValidDataAdd(AttributeValueRequest request){
        if (attributeValueRepository.existsByValueAndAttributeId(request.getValue(), request.getAttributeId())) {
            throw BusinessException.badRequest("Value đã tồn tại trong attribute này");
        }
    }

    private AttributeEntity getAttributeById(Long id){
        return attributeRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> BusinessException.notFound("Attribute not found with id: " + id));
    }

    private AttributeValueEntity getActiveAttributeValue(Long id) {
        return attributeValueRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> BusinessException.notFound("AttributeValue not found with id: " + id));
    }
}

