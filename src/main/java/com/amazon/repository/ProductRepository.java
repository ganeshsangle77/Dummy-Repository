package com.amazon.repository;

import com.amazon.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByGenderCategory(String field);
    ProductEntity findWithPropertyPictureAttachedByItemId(Integer id);
}
