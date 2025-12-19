package com.t3h.projectclothes.dto.attributevalue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeValueDto {
    private Long id;
    private String code;
    private String value;
    private Long attributeId;
    private String attributeName;
}

