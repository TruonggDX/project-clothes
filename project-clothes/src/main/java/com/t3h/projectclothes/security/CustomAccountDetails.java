package com.t3h.projectclothes.security;

import com.t3h.projectclothes.entity.RoleEntity;
import com.t3h.projectclothes.entity.UserEntity;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomAccountDetails implements UserDetails {

  Long id;
  String email;
  String password;
  boolean enabled;
  Set<RoleEntity> roles;

  public static CustomAccountDetails fromUser(UserEntity user) {
    return new CustomAccountDetails(
        user.getId(),
        user.getEmail(),
        user.getPassword(),
        user.isEnabled(),
        user.getRoles()
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toSet());
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
