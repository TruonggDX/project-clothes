package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.role.RoleDto;
import com.t3h.projectclothes.dto.role.RoleRequest;
import com.t3h.projectclothes.service.RoleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {

  private final RoleService roleService;

  @GetMapping("/list")
  public ResponseEntity<List<RoleDto>> list() {
    List<RoleDto> roleDtos = roleService.getAll();
    return ResponseEntity.ok(roleDtos);
  }

  @PostMapping
  private ResponseEntity<RoleDto> create(@Valid @RequestBody RoleRequest request) {
    RoleDto dto = roleService.add(request);
    return ResponseEntity.ok(dto);
  }
}
