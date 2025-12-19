package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.AttributeValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValueEntity, Long> {
    
    @Query("SELECT av FROM AttributeValueEntity av WHERE av.isDeleted = false")
    List<AttributeValueEntity> getAllAttributeValue();
    
    @Query("SELECT av FROM AttributeValueEntity av WHERE av.isDeleted = false AND av.attribute.id = :attributeId")
    List<AttributeValueEntity> findByAttributeId(Long attributeId);
    
    Optional<AttributeValueEntity> findByIdAndIsDeletedFalse(Long id);
    
    boolean existsByValueAndAttributeId(String value, Long attributeId);
}

