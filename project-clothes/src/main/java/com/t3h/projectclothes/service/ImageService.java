package com.t3h.projectclothes.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageService {

    String upload(MultipartFile file) throws IOException;

    void deleteImage(String publicId);
}

