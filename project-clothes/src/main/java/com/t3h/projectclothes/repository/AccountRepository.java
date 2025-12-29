package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmailAndIsDeletedFalse(String email);
}
