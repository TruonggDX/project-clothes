package com.t3h.projectclothes.service;

import com.t3h.projectclothes.dto.category.CategoryDto;
import com.t3h.projectclothes.dto.category.CategoryRequest;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll();

    CategoryDto getById(Long id);

    CategoryDto add(CategoryRequest request);

    CategoryDto update(Long id, CategoryRequest request);

    void delete(Long id);
}




