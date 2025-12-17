package com.t3h.projectclothes.dto.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BrandRequest {
    @NotBlank(message = "Name không được để trống")
    @Size(max = 50, message = "Name không được quá 50 ký tự")
    private String name;
}
