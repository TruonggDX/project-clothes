package com.t3h.projectclothes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends BaseEntity {

  @Column(unique = true, nullable = false)
  String code;

  @Column(nullable = false, unique = true)
  String name;

  @ManyToMany(mappedBy = "roles")
  Set<UserEntity> users = new HashSet<>();
}

