package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    @Query("SELECT i FROM ImageEntity i WHERE i.product.id=:id")
    List<ImageEntity> findImageEntityByProductId(Long id);
}
