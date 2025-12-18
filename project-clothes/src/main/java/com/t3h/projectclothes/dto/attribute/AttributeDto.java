package com.t3h.projectclothes.dto.attribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeDto {
    private Long id;
    private String code;
    private String name;
}
