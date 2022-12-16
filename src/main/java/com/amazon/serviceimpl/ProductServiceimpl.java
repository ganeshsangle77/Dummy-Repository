package com.amazon.serviceimpl;

import com.amazon.entity.ProductEntity;
import com.amazon.repository.ProductRepository;
import com.amazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceimpl implements ProductService {
    @Autowired
    ProductRepository repo;
    List<ProductEntity> productList;

    @Override
    public List<ProductEntity> getAllProducts() {
        productList = repo.findAll();
        return productList;
    }

    @Override
    public List<ProductEntity> findByGenderCategory(String field) {
        return productList.stream().filter(x->x.getGenderCategory().equals(field)).collect(Collectors.toList());
    }
}
