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
    Page<ProductEntity> products = productRepository.getAllProduct(
        filter.getCategoryId(),
        filter.getBrandId(),
        filter.getStatus(),
        filter.getCode(),
        filter.getName(),
        pageable);
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
    setCategoryAndBrand(productRequest,product);
    productRepository.save(product);
    if (files != null && !files.isEmpty()) {
      List<ImageEntity> imageEntities = uploadImage(files, product);
      imageRepository.saveAll(imageEntities);
    }
    return productMapper.toDto(product);
  }

  private void setCategoryAndBrand(ProductRequest productRequest, ProductEntity product){
    CategoryEntity category = getCategoryById(productRequest.getCategoryId());
    BrandEntity brand = getBrandById(productRequest.getBrandId());
    product.setCategory(category);
    product.setBrand(brand);
  }

  @Override
  public ProductDto updateProduct(Long id, ProductRequest productRequest, List<MultipartFile> files) {
    ProductEntity product = getActiveProduct(id);
    productMapper.updateProduct(productRequest, product);
    setCategoryAndBrand(productRequest,product);
    updateImage(id,product,files);
    productRepository.save(product);
    return productMapper.toDto(product);
  }

  private void updateImage(Long id,ProductEntity product,List<MultipartFile> files){
    List<ImageEntity> entities = imageRepository.findImageEntityByProductId(id);
    for (ImageEntity image : entities){
      if (image.getPublicId() != null && !image.getPublicId().isEmpty()) {
        imageService.deleteImage(image.getPublicId());
      }
    }
    imageRepository.deleteAll(entities);
    product.getImages().clear();
    if (files != null && !files.isEmpty()) {
      List<ImageEntity> imageEntities = uploadImage(files, product);
      imageRepository.saveAll(imageEntities);
    }
  }

  @Override
  public void deleteProduct(Long id) {
    ProductEntity product = getActiveProduct(id);
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
}
