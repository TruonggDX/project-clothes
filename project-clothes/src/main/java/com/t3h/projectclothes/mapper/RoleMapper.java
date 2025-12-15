package com.t3h.projectclothes.mapper;

import com.t3h.projectclothes.dto.role.RoleDto;
import com.t3h.projectclothes.dto.role.RoleRequest;
import com.t3h.projectclothes.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {

  RoleDto toDto(RoleEntity roleEntity);

  RoleEntity toEntity(RoleDto roleDto);

  RoleEntity toRequest(RoleRequest roleRequest);

  void toUpdate(RoleDto roleDto, @MappingTarget RoleEntity roleEntity);
}
