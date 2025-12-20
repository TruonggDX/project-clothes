package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c WHERE c.isDeleted = false")
    List<CategoryEntity> getAllCategory();

    Optional<CategoryEntity> findByIdAndIsDeletedFalse(Long id);

    boolean existsByName(String name);
}




