package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.RoleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  @Query(value = "SELECT r FROM RoleEntity r WHERE r.isDeleted=false")
  List<RoleEntity> getALlRole();
}
