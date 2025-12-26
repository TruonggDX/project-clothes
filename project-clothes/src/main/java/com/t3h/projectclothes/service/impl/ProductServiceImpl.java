package com.t3h.projectclothes.service.impl;

import com.t3h.projectclothes.dto.product.ProductDto;
import com.t3h.projectclothes.dto.product.ProductFilter;
import com.t3h.projectclothes.dto.product.ProductRequest;
import com.t3h.projectclothes.entity.BrandEntity;
import com.t3h.projectclothes.entity.CategoryEntity;
import com.t3h.projectclothes.entity.ImageEntity;
import com.t3h.projectclothes.entity.ProductEntity;
import com.t3h.projectclothes.exception.BusinessException;
import com.t3h.projectclothes.mapper.ProductMapper;
import com.t3h.projectclothes.repository.BrandRepository;
import com.t3h.projectclothes.repository.CategoryRepository;
import com.t3h.projectclothes.repository.ImageRepository;
import com.t3h.projectclothes.repository.ProductRepository;
import com.t3h.projectclothes.service.ImageService;
import com.t3h.projectclothes.service.ProductService;
import com.t3h.projectclothes.utils.GenerateCode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CategoryRepository categoryRepository;
  private final BrandRepository brandRepository;
  private final ImageRepository imageRepository;
  private final ImageService imageService;

  @Override
  public Page<ProductDto> getAllProducts(ProductFilter filter, Pageable pageable) {
    Page<ProductEntity> products = productRepository.getAllProduct(filter.getCategoryId(),
        filter.getBrandId(), pageable);
    return products.map(productMapper::toDto);
  }

  @Override
  public ProductDto getById(Long id) {
    ProductEntity product = getActiveProduct(id);
    return productMapper.toDto(product);
  }

  @Override
  public ProductDto addProduct(ProductRequest productRequest, List<MultipartFile> files) {
    ProductEntity product = productMapper.toEntity(productRequest);
    product.setCode(GenerateCode.generateCode());
    product.setIsDeleted(false);
    CategoryEntity category = getCategoryById(productRequest.getCategoryId());
    BrandEntity brand = getBrandById(productRequest.getBrandId());
    product.setCategory(category);
    product.setBrand(brand);
    productRepository.save(product);
    if (files != null && !files.isEmpty()) {
      List<ImageEntity> imageEntities = uploadImage(files, product);
      imageRepository.saveAll(imageEntities);
    }
    return productMapper.toDto(product);
  }

  @Override
  public ProductDto updateProduct(Long id, ProductRequest productRequest, List<MultipartFile> files) {
    ProductEntity product = getActiveProduct(id);
    
    // Xóa ảnh cũ trên cloud trước khi update
    deleteOldImagesFromCloud(product);
    
    // Update thông tin product
    productMapper.updateProduct(productRequest, product);
    CategoryEntity category = getCategoryById(productRequest.getCategoryId());
    BrandEntity brand = getBrandById(productRequest.getBrandId());
    product.setCategory(category);
    product.setBrand(brand);
    
    // Xóa ảnh cũ trong database
    imageRepository.deleteAll(product.getImages());
    product.getImages().clear();
    
    // Upload ảnh mới nếu có
    if (files != null && !files.isEmpty()) {
      List<ImageEntity> imageEntities = uploadImage(files, product);
      imageRepository.saveAll(imageEntities);
    }
    
    productRepository.save(product);
    return productMapper.toDto(product);
  }

  @Override
  public void deleteProduct(Long id) {
    ProductEntity product = getActiveProduct(id);
    
    // Xóa ảnh trên cloud trước khi xóa product
    deleteOldImagesFromCloud(product);
    
    // Soft delete product
    product.setIsDeleted(true);
    productRepository.save(product);
  }

  private ProductEntity getActiveProduct(Long id) {
    return productRepository.findByIdAndIsDeletedFalse(id)
        .orElseThrow(() -> BusinessException.notFound("Product not found with id: " + id));
  }

  private CategoryEntity getCategoryById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> BusinessException.notFound("Category not found with id: " + id));
  }

  private BrandEntity getBrandById(Long id) {
    return brandRepository.findById(id)
        .orElseThrow(() -> BusinessException.notFound("Brand not found with id: " + id));
  }

  private List<ImageEntity> uploadImage(List<MultipartFile> files, ProductEntity product) {
    List<ImageEntity> images = new ArrayList<>();
    if (files != null) {
      for (MultipartFile file : files) {
        try {
          String fileImage = imageService.upload(file);
          ImageEntity image = new ImageEntity();
          image.setImageUrl(fileImage);
          image.setProduct(product);
          images.add(image);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
    return images;
  }

  /**
   * Xóa ảnh cũ trên cloud trước khi update hoặc delete product
   */
  private void deleteOldImagesFromCloud(ProductEntity product) {
    if (product.getImages() != null && !product.getImages().isEmpty()) {
      for (ImageEntity image : product.getImages()) {
        if (image.getImageUrl() != null) {
          String publicId = extractPublicIdFromUrl(image.getImageUrl());
          if (publicId != null && !publicId.isEmpty()) {
            try {
              imageService.deleteImage(publicId);
            } catch (Exception e) {
              // Log error nhưng không throw để không block quá trình update
              // Có thể log ở đây nếu cần
            }
          }
        }
      }
    }
  }

  /**
   * Extract publicId từ Cloudinary URL
   * URL format: https://res.cloudinary.com/{cloud_name}/image/upload/{version}/{public_id}.{extension}
   */
  private String extractPublicIdFromUrl(String imageUrl) {
    if (imageUrl == null || imageUrl.isEmpty()) {
      return null;
    }
    
    try {
      // Tìm phần sau "/upload/" hoặc "/upload/v{version}/"
      int uploadIndex = imageUrl.indexOf("/upload/");
      if (uploadIndex == -1) {
        return null;
      }
      
      String afterUpload = imageUrl.substring(uploadIndex + "/upload/".length());
      
      // Bỏ qua version nếu có (format: v1234567890/)
      if (afterUpload.startsWith("v") && afterUpload.contains("/")) {
        int versionEnd = afterUpload.indexOf("/");
        afterUpload = afterUpload.substring(versionEnd + 1);
      }
      
      // Lấy publicId (bỏ extension)
      int lastDot = afterUpload.lastIndexOf(".");
      if (lastDot != -1) {
        return afterUpload.substring(0, lastDot);
      }
      
      return afterUpload;
    } catch (Exception e) {
      return null;
    }
  }
}
