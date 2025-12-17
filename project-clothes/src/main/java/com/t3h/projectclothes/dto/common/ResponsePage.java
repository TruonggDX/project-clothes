package com.t3h.projectclothes.dto.common;

import lombok.Data;

@Data
public class ResponsePage<T> {

  private T content;
  private int pageNumber;
  private int pageSize;
  private long totalElements;
  private int totalPages;
}
