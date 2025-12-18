package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.dto.image.ImageUploadResponse;
import com.t3h.projectclothes.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {

    private final ImageService imageService;

    /**
     * Upload ảnh lên Cloudinary
     * POST /api/v1/image/upload
     * Content-Type: multipart/form-data
     * Body: file (form-data)
     */
    @PostMapping("/upload")
    public ResponseEntity<BaseResponse<ImageUploadResponse>> uploadImage(
            @RequestParam("file") MultipartFile file
    ) {
        Map<String, Object> uploadResult = imageService.uploadImage(file);

        ImageUploadResponse response = ImageUploadResponse.builder()
                .url((String) uploadResult.get("url"))
                .publicId((String) uploadResult.get("public_id"))
                .format((String) uploadResult.get("format"))
                .bytes(((Number) uploadResult.get("bytes")).longValue())
                .width((Integer) uploadResult.get("width"))
                .height((Integer) uploadResult.get("height"))
                .build();

        return ResponseEntity.ok(BaseResponse.success(response));
    }

    /**
     * Xóa ảnh trên Cloudinary
     * DELETE /api/v1/image?publicId=project-clothes/abc123
     */
    @DeleteMapping
    public ResponseEntity<BaseResponse<Map<String, Object>>> deleteImage(
            @RequestParam("publicId") String publicId
    ) {
        Map<String, Object> deleteResult = imageService.deleteImage(publicId);
        return ResponseEntity.ok(BaseResponse.success(deleteResult));
    }
}

