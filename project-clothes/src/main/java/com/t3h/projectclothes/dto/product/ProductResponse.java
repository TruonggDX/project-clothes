package com.t3h.projectclothes.dto.product;

import lombok.Data;

@Data
public class ProductResponse {

  private Long id;
  private String code;
  private String name;
  private String description;
}
