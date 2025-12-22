package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.ProductVariantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariantEntity, Long> {
    @Query("SELECT pv FROM ProductVariantEntity pv WHERE pv.isDeleted = false")
    Page<ProductVariantEntity> getAllProductVariant(Pageable pageable);
    
    @Query("SELECT pv FROM ProductVariantEntity pv WHERE pv.isDeleted = false AND pv.product.id = :productId")
    List<ProductVariantEntity> findByProductId(Long productId);
    
    Optional<ProductVariantEntity> findByIdAndIsDeletedFalse(Long id);
    
    boolean existsByCode(String code);
}

