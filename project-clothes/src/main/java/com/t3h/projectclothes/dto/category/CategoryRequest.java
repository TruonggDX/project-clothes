package com.t3h.projectclothes.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {

    @NotBlank(message = "Name không được để trống")
    @Size(max = 100, message = "Name không được quá 100 ký tự")
    private String name;

    private Long parentId;
}




