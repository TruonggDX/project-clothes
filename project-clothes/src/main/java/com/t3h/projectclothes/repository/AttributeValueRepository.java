package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.AttributeValueEntity;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT  av FROM AttributeValueEntity av WHERE av.id IN :ids AND av.isDeleted=false")
    List<AttributeValueEntity> findByIds(@Param("ids") Set<Long> ids);
    
    boolean existsByValueAndAttributeId(String value, Long attributeId);
}

