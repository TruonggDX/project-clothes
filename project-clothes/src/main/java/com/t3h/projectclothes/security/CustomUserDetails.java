package com.t3h.projectclothes.security;

import com.t3h.projectclothes.entity.UserEntity;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

  private static final long serialVersionUID = 1L;
  private final UserEntity userEntity;
  private final Collection<? extends GrantedAuthority> authorities;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public CustomUserDetails(UserEntity userEntity) {
    this.userEntity = userEntity;
    this.authorities = userEntity.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(
            Collectors.toSet());
  }

  @Override
  public String getPassword() {
    return userEntity.getPassword();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public String getUsername() {
    return userEntity.getEmail();
  }
}
