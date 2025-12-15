package com.t3h.projectclothes.service;

import com.t3h.projectclothes.dto.role.RoleDto;
import com.t3h.projectclothes.dto.role.RoleRequest;
import java.util.List;

public interface RoleService {

  List<RoleDto> getAll();

  RoleDto add(RoleRequest request);
}
