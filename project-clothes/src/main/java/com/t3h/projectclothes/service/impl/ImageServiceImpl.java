package com.t3h.projectclothes.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.t3h.projectclothes.exception.BusinessException;
import com.t3h.projectclothes.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final Cloudinary cloudinary;

    @Override
    public Map<String, Object> uploadImage(MultipartFile file) {
        try {
            // Validate file
            if (file.isEmpty()) {
                throw BusinessException.badRequest("File không được để trống");
            }

            // Validate file type
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw BusinessException.badRequest("File phải là ảnh");
            }

            // Validate file size (max 10MB)
            if (file.getSize() > 10 * 1024 * 1024) {
                throw BusinessException.badRequest("File không được vượt quá 10MB");
            }

            // Upload to Cloudinary
            Map<String, Object> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "project-clothes", // Folder trên Cloudinary
                            "resource_type", "auto" // Tự động detect loại file
                    )
            );

            log.info("Upload image successful: {}", uploadResult.get("url"));
            return uploadResult;

        } catch (IOException e) {
            log.error("Error uploading image: {}", e.getMessage());
            throw BusinessException.badRequest("Lỗi khi upload ảnh: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteImage(String publicId) {
        try {
            Map<String, Object> deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            log.info("Delete image successful: {}", publicId);
            return deleteResult;
        } catch (IOException e) {
            log.error("Error deleting image: {}", e.getMessage());
            throw BusinessException.badRequest("Lỗi khi xóa ảnh: " + e.getMessage());
        }
    }
}

