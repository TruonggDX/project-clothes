package com.t3h.projectclothes.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequest {

  @NotBlank(message = "Name not null")
  @Size(message = "min 8 character")
  private String name;
}
