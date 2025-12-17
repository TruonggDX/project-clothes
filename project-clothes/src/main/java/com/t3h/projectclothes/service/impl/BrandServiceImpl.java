package com.t3h.projectclothes.service.impl;

import com.t3h.projectclothes.dto.brand.BrandDto;
import com.t3h.projectclothes.dto.brand.BrandRequest;
import com.t3h.projectclothes.entity.BrandEntity;
import com.t3h.projectclothes.exception.BusinessException;
import com.t3h.projectclothes.mapper.BrandMapper;
import com.t3h.projectclothes.repository.BrandRepository;
import com.t3h.projectclothes.service.BrandService;
import com.t3h.projectclothes.utils.GenerateCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public List<BrandDto> getAll() {
        List<BrandEntity> entities = brandRepository.getAllBrand();
        return entities.stream().map(brandMapper::toDto).toList();
    }

    @Override
    public BrandDto getById(Long id) {
        BrandEntity entity = getActiveBrand(id);
        return brandMapper.toDto(entity);
    }

    @Override
    public BrandDto add(BrandRequest brandRequest) {
        BrandEntity entity = brandMapper.toEntity(brandRequest);
        entity.setCode(GenerateCode.generateCode());
        entity.setIsDeleted(false);
        return brandMapper.toDto(brandRepository.save(entity));
    }

    @Override
    public BrandDto update(Long id, BrandRequest brandRequest) {
        BrandEntity entity = getActiveBrand(id);
        brandMapper.updateBrand(brandRequest, entity);
        return brandMapper.toDto(brandRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        BrandEntity entity = getActiveBrand(id);
        entity.setIsDeleted(true);
        brandRepository.save(entity);
    }

    private BrandEntity getActiveBrand(Long id) {
        return brandRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> BusinessException.notFound("Brand not found with id: " + id));
    }
}
