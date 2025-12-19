package com.t3h.projectclothes.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageService {

    /**
     * Upload ảnh lên Cloudinary
     * @param file File ảnh cần upload
     * @return Map chứa thông tin ảnh đã upload (url, public_id, ...)
     */
    Map<String, Object> uploadImage(MultipartFile file);

    /**
     * Xóa ảnh trên Cloudinary
     * @param publicId Public ID của ảnh trên Cloudinary
     * @return Map chứa kết quả xóa
     */
    Map<String, Object> deleteImage(String publicId);
}

