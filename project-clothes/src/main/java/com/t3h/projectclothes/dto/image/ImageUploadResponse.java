package com.t3h.projectclothes.dto.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadResponse {
    private String url;
    private String publicId;
    private String format;
    private Long bytes;
    private Integer width;
    private Integer height;
}

