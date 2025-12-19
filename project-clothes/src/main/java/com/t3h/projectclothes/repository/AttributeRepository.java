package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity, Long> {
    
    @Query("SELECT a FROM AttributeEntity a WHERE a.isDeleted = false")
    List<AttributeEntity> getAllAttribute();
    
    Optional<AttributeEntity> findByIdAndIsDeletedFalse(Long id);
    
    boolean existsByName(String name);
}

