package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.ProductEntity;
import com.t3h.projectclothes.utils.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  @Query("SELECT p FROM ProductEntity p " +
      "WHERE p.isDeleted = false " +
      "AND (:categoryId IS NULL OR p.category.id = :categoryId) " +
      "AND (:brandId IS NULL OR p.brand.id = :brandId) " +
      "AND (:status IS NULL OR p.status = :status) " +
      "AND (:code IS NULL OR LOWER(p.code) LIKE LOWER(CONCAT('%', :code, '%'))) " +
      "AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))")
  Page<ProductEntity> getAllProduct(
      @Param("categoryId") Long categoryId,
      @Param("brandId") Long brandId,
      @Param("status") ProductStatus status,
      @Param("code") String code,
      @Param("name") String name,
      Pageable pageable);

  Optional<ProductEntity> findByIdAndIsDeletedFalse(Long id);

  boolean existsByCode(String code);
}
