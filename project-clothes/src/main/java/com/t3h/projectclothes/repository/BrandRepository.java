package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    @Query(value = "SELECT b FROM BrandEntity b WHERE b.isDeleted=false ")
    List<BrandEntity> getAllBrand();
    Optional<BrandEntity> findByIdAndIsDeletedFalse(Long id);
    boolean existsByName(String name);
}
