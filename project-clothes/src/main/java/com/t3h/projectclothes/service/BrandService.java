package com.t3h.projectclothes.service;

import com.t3h.projectclothes.dto.brand.BrandDto;
import com.t3h.projectclothes.dto.brand.BrandRequest;

import java.util.List;

public interface BrandService {

  List<BrandDto> getAll();

  BrandDto getById(Long id);

  BrandDto add(BrandRequest brandRequest);

  BrandDto update(Long id, BrandRequest brandRequest);

  void delete(Long id);
}
