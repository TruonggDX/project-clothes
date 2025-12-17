package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.dto.role.RoleDto;
import com.t3h.projectclothes.dto.role.RoleRequest;
import com.t3h.projectclothes.service.RoleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {

  private final RoleService roleService;

  @GetMapping("/list")
  public ResponseEntity<BaseResponse<List<RoleDto>>> list() {
    return ResponseEntity.ok(BaseResponse.success(roleService.getAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<RoleDto>> getById(@PathVariable Long id) {
    return ResponseEntity.ok(BaseResponse.success(roleService.getById(id)));
  }

  @PostMapping
  public ResponseEntity<BaseResponse<RoleDto>> create(@Valid @RequestBody RoleRequest request) {
    return ResponseEntity.ok(BaseResponse.success(roleService.add(request)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse<RoleDto>> update(
      @PathVariable Long id, @Valid @RequestBody RoleRequest request) {
    return ResponseEntity.ok(BaseResponse.success(roleService.update(id, request)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id) {
    roleService.delete(id);
    return ResponseEntity.ok(BaseResponse.success(null));
  }
}
