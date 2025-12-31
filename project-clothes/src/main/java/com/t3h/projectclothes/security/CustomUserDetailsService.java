package com.t3h.projectclothes.security;

import com.t3h.projectclothes.entity.UserEntity;
import com.t3h.projectclothes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = repository.findByEmailWithRoles(username)
        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    return new CustomUserDetails(userEntity);
  }
}
