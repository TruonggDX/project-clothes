package com.t3h.projectclothes.service.impl;

import com.t3h.projectclothes.dto.role.RoleDto;
import com.t3h.projectclothes.dto.role.RoleRequest;
import com.t3h.projectclothes.entity.RoleEntity;
import com.t3h.projectclothes.mapper.RoleMapper;
import com.t3h.projectclothes.repository.RoleRepository;
import com.t3h.projectclothes.service.RoleService;
import com.t3h.projectclothes.utils.GenerateCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;

  @Override
  public List<RoleDto> getAll() {
    List<RoleEntity> roleEntities = roleRepository.getALlRole();
    return roleEntities.stream().map(roleMapper::toDto).toList();
  }

  @Override
  public RoleDto add(RoleRequest request) {
    RoleEntity roleEntity = roleMapper.toRequest(request);
    roleEntity.setCode(GenerateCode.generateCode());
    return roleMapper.toDto(roleRepository.save(roleEntity));
  }
}
