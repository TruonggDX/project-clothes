package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  @Query("SELECT p FROM ProductEntity p WHERE p.isDeleted = false AND p.category.id=:categoryId OR p.brand.id=:brandId")
  Page<ProductEntity> getAllProduct(Long categoryId, Long brandId, Pageable pageable);

  Optional<ProductEntity> findByIdAndIsDeletedFalse(Long id);

  boolean existsByCode(String code);
}

