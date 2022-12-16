package com.amazon.service;

import com.amazon.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<ProductEntity> getAllProducts();

    List<ProductEntity> findByGenderCategory(String field);
}
