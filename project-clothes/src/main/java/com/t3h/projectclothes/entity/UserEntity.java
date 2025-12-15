package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {

  @Column(unique = true, nullable = false)
  String code;

  @Column(nullable = false)
  String name;

  @Column(nullable = false, unique = true)
  String email;

  @Column(nullable = false)
  String password;

  @Column(unique = true)
  String phone;

  boolean enabled;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  @Builder.Default
  Set<RoleEntity> roles = new HashSet<>();
}

