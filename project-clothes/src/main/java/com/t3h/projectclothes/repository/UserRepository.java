package com.t3h.projectclothes.repository;

import com.t3h.projectclothes.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles WHERE u.email = :email")
  Optional<UserEntity> findByEmailWithRoles(@Param("email") String email);
}
