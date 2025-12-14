package com.t3h.projectclothes.entity;

import com.t3h.projectclothes.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserEntity extends BaseEntity {

    @Column(nullable = false, length = 255)
    String name;

    @Column(nullable = false, unique = true, length = 255)
    String email;

    @Column(nullable = false, length = 255)
    String password;

    @Column(length = 255)
    String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserStatus status;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Builder.Default
    Set<RoleEntity> roles = new HashSet<>();
}

