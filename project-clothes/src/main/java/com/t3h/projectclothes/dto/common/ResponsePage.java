package com.t3h.projectclothes.dto.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePage<T> {

  private List<T> content;
  private int pageNumber;
  private int pageSize;
  private long totalElements;
  private int totalPages;

  public static <T> ResponsePage<T> from(Page<T> page) {
    ResponsePage<T> response = new ResponsePage<>();
    response.setContent(page.getContent());
    response.setPageNumber(page.getNumber());
    response.setPageSize(page.getSize());
    response.setTotalElements(page.getTotalElements());
    response.setTotalPages(page.getTotalPages());
    return response;
  }
}
