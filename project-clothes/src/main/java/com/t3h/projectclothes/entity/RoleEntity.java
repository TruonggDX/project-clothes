package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class RoleEntity extends BaseEntity {

    @Column(nullable = false, unique = true, length = 255)
    String name;

    @ManyToMany(mappedBy = "roles")
    @Builder.Default
    Set<UserEntity> users = new HashSet<>();
}

