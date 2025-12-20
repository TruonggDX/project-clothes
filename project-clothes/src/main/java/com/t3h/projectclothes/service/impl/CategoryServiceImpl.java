package com.t3h.projectclothes.service.impl;

import com.t3h.projectclothes.dto.category.CategoryDto;
import com.t3h.projectclothes.dto.category.CategoryRequest;
import com.t3h.projectclothes.entity.CategoryEntity;
import com.t3h.projectclothes.exception.BusinessException;
import com.t3h.projectclothes.mapper.CategoryMapper;
import com.t3h.projectclothes.repository.CategoryRepository;
import com.t3h.projectclothes.service.CategoryService;
import com.t3h.projectclothes.utils.GenerateCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryEntity> entities = categoryRepository.getAllCategory();
        return entities.stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        CategoryEntity entity = getActiveCategory(id);
        return categoryMapper.toDto(entity);
    }

    @Override
    public CategoryDto add(CategoryRequest request) {
        CategoryEntity entity = categoryMapper.toEntity(request);
        entity.setCode(GenerateCode.generateCode());
        entity.setIsDeleted(false);
        // xử lý parent nếu có
        if (request.getParentId() != null) {
            CategoryEntity parent = getActiveCategory(request.getParentId());
            entity.setParent(parent);
        }
        return categoryMapper.toDto(categoryRepository.save(entity));
    }

    @Override
    public CategoryDto update(Long id, CategoryRequest request) {
        CategoryEntity entity = getActiveCategory(id);
        categoryMapper.updateCategory(request, entity);
        if (request.getParentId() != null) {
            CategoryEntity parent = getActiveCategory(request.getParentId());
            entity.setParent(parent);
        } else {
            entity.setParent(null);
        }
        return categoryMapper.toDto(categoryRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        CategoryEntity entity = getActiveCategory(id);
        entity.setIsDeleted(true);
        categoryRepository.save(entity);
    }

    private CategoryEntity getActiveCategory(Long id) {
        return categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> BusinessException.notFound("Category not found with id: " + id));
    }
}




