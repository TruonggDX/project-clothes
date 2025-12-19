package com.t3h.projectclothes.dto.attributevalue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AttributeValueRequest {
    
    @NotBlank(message = "Value không được để trống")
    @Size(max = 255, message = "Value không được quá 255 ký tự")
    private String value;
    
    @NotNull(message = "Attribute ID không được để trống")
    private Long attributeId;
}

